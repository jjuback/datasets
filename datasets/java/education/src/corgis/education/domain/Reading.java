package corgis.education.domain;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


/**
 * 
 */
public class Reading {
	
    private Integer grade;
    private Integer scaleScore;
    
    
    /**
     * 
     * @return Integer
     */
    public Integer getGrade() {
        return this.grade;
    }
    
    
    
    /**
     * 
     * @return Integer
     */
    public Integer getScaleScore() {
        return this.scaleScore;
    }
    
    
    
	
	/**
	 * Creates a string based representation of this Reading.
	
	 * @return String
	 */
	public String toString() {
		return "Reading[" +grade+", "+scaleScore+"]";
	}
	
	/**
	 * Internal constructor to create a Reading from a  representation.
	 * @param json_data The raw json data that will be parsed.
	 */
    public Reading(JSONObject json_data) {
        //System.out.println(json_data);
        
        try {
            // grade
            this.grade = ((Number)json_data.get("grade")).intValue();
        } catch (NullPointerException e) {
    		System.err.println("Could not convert the response to a Reading; the field grade was missing.");
    		e.printStackTrace();
    	} catch (ClassCastException e) {
    		System.err.println("Could not convert the response to a Reading; the field grade had the wrong structure.");
    		e.printStackTrace();
        }
        
        try {
            // scale score
            this.scaleScore = ((Number)json_data.get("scale score")).intValue();
        } catch (NullPointerException e) {
    		System.err.println("Could not convert the response to a Reading; the field scaleScore was missing.");
    		e.printStackTrace();
    	} catch (ClassCastException e) {
    		System.err.println("Could not convert the response to a Reading; the field scaleScore had the wrong structure.");
    		e.printStackTrace();
        }
        
	}	
}