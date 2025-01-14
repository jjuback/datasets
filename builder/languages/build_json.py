from __future__ import print_function
import json
import csv
import sys, os
from pprint import pprint
from itertools import tee
try:
    from itertools import izip
except ImportError:
    izip = zip
from textwrap import wrap
from collections import OrderedDict
from auxiliary import to_dict, camel_case_caps, camel_case
from auxiliary import snake_case, kebab_case, flat_case, copy_file
import sqlite3
import re
from jinja2 import Environment, FileSystemLoader
import jinja2_highlight

base_directory = os.path.dirname(os.path.realpath(__file__))
json_templates = os.path.join(base_directory, 'json/')
templates = os.path.join(base_directory, 'templates/')

env = Environment(extensions=['jinja2_highlight.HighlightExtension'], 
                  loader=FileSystemLoader([json_templates, templates]))
env.filters['camel_case_caps'] = camel_case_caps
env.filters['camel_case'] = camel_case
env.filters['snake_case'] = snake_case
env.filters['kebab_case'] = kebab_case
env.filters['flat_case'] = flat_case

env.filters['sluggify'] = lambda x: x
env.filters['to_human_readable_type'] = lambda x: x
env.filters['convert_example_value'] = lambda x: x
env.filters['wrap_quotes'] = lambda x: x

ordinals = ["first", "second", "third", "fourth", "fifth", "sixth", "seventh", "eighth", "ninth", 
            "tenth", "eleventh", "twelfth", "thirteenth", "fourteenth", "fifteenth", "sixteenth",
            "seventeenth", "eighteenth", "nineteenth", "twentieith", "twenty-first"]
            
class JsonLeafNodes(object):    
    def __init__(self, name, data):
        self.result = {}
        self.path = []
        self.name = name
        self.walk(data, name)
    
    @property
    def json_path(self):
        return ".".join([self.name]+self.path)
        
    def type_check(self, value):
        return value.__class__.__name__
        
    def walk(self, chunk, parent_name):
        if isinstance(chunk, dict):
            self.walk_dict(chunk, parent_name)
        elif isinstance(chunk, list):
            self.walk_list(chunk, parent_name)
        else:
            self.walk_atomic(chunk, parent_name)
        return self
        
    def walk_dict(self, a_dict, parent_name):
        for key, value in a_dict.items():
            self.path.append(key)
            self.walk(value, key)
            self.path.pop()
    def walk_list(self, a_list, parent_name):
        return        
        if not a_list:
            return
        self.path.append("[0]")
        first = a_list[0]
        self.walk(first, parent_name)
        self.path.pop()
    def walk_atomic(self, an_atomic, parent_name):
        if isinstance(an_atomic, (float, int, str, unicode)):
            self.result[self.json_path] = an_atomic
            
def lod_to_dol(LD):
    dictionaires = {}
    for row in LD:
        for key, value in row.items():
            if key in dictionaires:
                dictionaires[key].append(value)
            else:
                dictionaires[key] = [value]
    return [{'name': k, 'data': v} for k,v in dictionaires.items()]

def flatten_json(data, delim="_"):
    result = {}
    def recursive_walk(key, value):
        if isinstance(value, dict):
            for inner_key, inner_value in value.items():
                recursive_walk(key+delim+inner_key, inner_value)
        elif isinstance(value, list):
            # TODO: Handle case where inner lists are not constant sized. 
            for ordinal, element in zip(ordinals, value):
                recursive_walk(key+delim+ordinal, element)
        else:
            result[key] = value
    recursive_walk("", data)
    return {k[1:] : v for k, v in result.items()}
    
def _byteify(input):
    """
    Force the given input to only use `str` instead of `bytes` or `unicode`.
    This works even if the input is a dict, list,
    """
    if isinstance(input, dict):
        return {_byteify(key): _byteify(value) for key, value in input.items()}
    elif isinstance(input, list):
        return [_byteify(element) for element in input]
    elif isinstance(input, unicode):
        return str(input.encode('ascii', 'replace').decode('ascii'))
    else:
        return input
    
def json_path(path, data):
    entries = path.split(".")
    for entry in entries:
        if entry.startswith("["):
            entry = int(entry[1:-1])
        data = data[entry]
    return data

def kill_unicode(value):
    if isinstance(value, unicode):
        return value.encode('ascii', 'ignore')
    return value
    
def _guess_schema(input):
    if isinstance(input, dict):
        return {str(key.encode('ascii', 'replace').decode('ascii')): 
                _guess_schema(value) for key, value in input.items()}
    elif isinstance(input, list):
        return [_guess_schema(input[0])] if input else []
    else:
        return type(input)
        
def first_items(a_list_of_tuples):
    return [item[0] for item in a_list_of_tuples]

def shortest_unique_strings(los):
    splits = [l.split('.') for l in los]
    lengths = [ (l[-1], l[:-1]) for l in splits]
    while len(first_items(lengths)) != len(set(first_items(lengths))):
        for this_index, (this, this_rest) in enumerate(lengths):
            for other_index, (other, other_rest) in enumerate(lengths):
                if this == other and this_index != other_index:
                    if len(this_rest) >= len(other_rest):
                        lengths[this_index] = ( this_rest[-1] + '.' + this , this_rest[:-1] )
                    if len(this_rest) <= len(other_rest):
                        lengths[other_index] = ( other_rest[-1] + '.' + other , other_rest[:-1] )
    return first_items(lengths)

def build_metafiles(model):
    name = snake_case(model['metadata']['name'])
    root = 'json/{name}/'.format(name=name)
    return {
            root+'index.html' : env.get_template('json_main.html').render(standalone=True, **model),
            root+name+'.html' : env.get_template('json_main.html').render(standalone=False, **model)
            }
            
def build_locals(model):
    locals = model["locals"]
    hardware = model['metadata']['hardware']
    module_name = snake_case(model['metadata']['name'])
    moves = {}
    
    for local in locals:
        name = module_name#snake_case(local["name"])
        new_file = name+'.json'
        file = local["file"]
        with open(file, 'rb') as inp, open(new_file, 'wb') as out:
            json.dump(json.load(inp), out, indent=2)
        moves[new_file] = 'json/'+module_name+'/'
        
    return moves

def build_json(model):
    files = {}
    files.update(build_metafiles(model))
    
    moves = {}
    moves.update(build_locals(model))
    for appendix in model['metadata']['appendix']:
        moves[appendix['file']] = 'json/' + snake_case(model['metadata']['name']) + '/'
        
    icon_file = model['metadata']['icon']
    name = snake_case(model['metadata']['name'])
    new_folder = 'json/' + name + '/'
    if os.path.exists(icon_file):
        with open(icon_file, 'rb') as icon_data:
            files[new_folder+name+'.png'] = icon_data.read()
    else:
        model["metadata"]["icon"] = False
    
    return files, moves