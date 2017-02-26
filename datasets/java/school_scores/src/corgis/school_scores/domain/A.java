package corgis.school_scores.domain;

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
public class A {
	
    // The average Math score of students in this state during this year who reported this for their high school grade point average.
    private Integer math;
    // The average Verbal (Reading, not Writing) score of students in this state during this year who reported this for their high school grade point average.
    private Integer verbal;
    // The number of test-takers in this state during this year who reported this for their high school grade point average.
    private Integer testTakers;
    
    
    /*
     * @return 
     */
    public Integer getMath() {
        return this.math;
    }
    
    
    
    /*
     * @return 
     */
    public Integer getVerbal() {
        return this.verbal;
    }
    
    
    
    /*
     * @return 
     */
    public Integer getTestTakers() {
        return this.testTakers;
    }
    
    
    
	
	/**
	 * Creates a string based representation of this A.
	
	 * @return String
	 */
	public String toString() {
		return "A[" +math+", "+verbal+", "+testTakers+"]";
	}
	
	/**
	 * Internal constructor to create a A from a  representation.
	 * @param json_data The raw json data that will be parsed.
	 */
    public A(JSONObject json_data) {
        try {// Math
            this.math = ((Number)json_data.get("Math")).intValue();// Verbal
            this.verbal = ((Number)json_data.get("Verbal")).intValue();// Test-takers
            this.testTakers = ((Number)json_data.get("Test-takers")).intValue();
        } catch (NullPointerException e) {
    		System.err.println("Could not convert the response to a A; a field was missing.");
    		e.printStackTrace();
    	} catch (ClassCastException e) {
    		System.err.println("Could not convert the response to a A; a field had the wrong structure.");
    		e.printStackTrace();
        }
	}	
}