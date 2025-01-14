from __future__ import print_function

import os
import shutil
import traceback
from zipfile import ZipFile
try:
    from StringIO import StringIO
except ImportError:
    from io import StringIO

def ensure_dir(f):
    d = os.path.dirname(f)
    if not os.path.exists(d):
        os.makedirs(d)
    return f

def build_zip(files):
    inMemoryOutputFile = StringIO()
    zipFile = ZipFile(inMemoryOutputFile, 'w') 
    error_log = []
    try:
        for filename, data in files.iteritems():
            zipFile.writestr(filename.encode('utf-8'), data.encode('utf8'))
    except Exception:
        error_log.append(traceback.format_exc().encode('utf-8'))
    if error_log:
        zipFile.writestr("error_log".encode('utf-8'), error_log)
    zipFile.close()
    inMemoryOutputFile.seek(0)
    data = inMemoryOutputFile.read()
    inMemoryOutputFile.close()
    return data, error_log

def build_dir(files, moves, prepend):
    error_log = []
    try:
        for filename, data in files.items():
            filename = os.path.join(prepend, filename)
            ensure_dir(filename)
            if isinstance(data, bytes):
                with open(filename, "wb+") as output:
                    output.write(data)
            else:
                with open(filename, "w+") as output:
                    output.write(data)
        for src, offset in moves.items():
            new_folder = prepend+'/'+offset
            ensure_dir(new_folder)
            if not os.path.isfile(src):
                print('Warning, file "{}" does not exist'.format(src))
            shutil.copy(src, new_folder)
            os.remove(src)
    except Exception:
        error_log.append(traceback.format_exc())
    if error_log:
        error_filename = os.path.join(prepend, "error_log")
        ensure_dir(error_filename)
        with open(error_filename, "w+") as error:
            error.write("\n\n".join(error_log))
    return "Completed", error_log
