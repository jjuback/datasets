package corgis.immigration.domain;

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
public class NonimmigrantAdmissions {
	
    private Integer birth;
    private Integer lastResidence;
    
    
    /*
     * @return 
     */
    public Integer getBirth() {
        return this.birth;
    }
    
    
    
    /*
     * @return 
     */
    public Integer getLastResidence() {
        return this.lastResidence;
    }
    
    
    
	
	/**
	 * Creates a string based representation of this NonimmigrantAdmissions.
	
	 * @return String
	 */
	public String toString() {
		return "NonimmigrantAdmissions[" +birth+", "+lastResidence+"]";
	}
	
	/**
	 * Internal constructor to create a NonimmigrantAdmissions from a  representation.
	 * @param json_data The raw json data that will be parsed.
	 */
    public NonimmigrantAdmissions(JSONObject json_data) {
        try {// Birth
            this.birth = ((Number)json_data.get("Birth")).intValue();// Last Residence
            this.lastResidence = ((Number)json_data.get("Last Residence")).intValue();
        } catch (NullPointerException e) {
    		System.err.println("Could not convert the response to a NonimmigrantAdmissions; a field was missing.");
    		e.printStackTrace();
    	} catch (ClassCastException e) {
    		System.err.println("Could not convert the response to a NonimmigrantAdmissions; a field had the wrong structure.");
    		e.printStackTrace();
        }
	}	
}