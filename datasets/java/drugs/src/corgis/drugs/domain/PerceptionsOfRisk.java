package corgis.drugs.domain;

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
public class PerceptionsOfRisk {
	
    // Percentage of the population that perceives great risk in Smoking Marijuana once a month, among this age group.
    private Double twosixplus;
    // Percentage of the population that perceives great risk in Smoking Marijuana once a month, among this age group.
    private Double oneeightTwofive;
    // Percentage of the population that perceives great risk in Smoking Marijuana once a month, among this age group.
    private Double onetwoOneseven;
    
    
    /*
     * @return 
     */
    public Double getTwosixplus() {
        return this.twosixplus;
    }
    
    
    
    /*
     * @return 
     */
    public Double getOneeightTwofive() {
        return this.oneeightTwofive;
    }
    
    
    
    /*
     * @return 
     */
    public Double getOnetwoOneseven() {
        return this.onetwoOneseven;
    }
    
    
    
	
	/**
	 * Creates a string based representation of this PerceptionsOfRisk.
	
	 * @return String
	 */
	public String toString() {
		return "PerceptionsOfRisk[" +twosixplus+", "+oneeightTwofive+", "+onetwoOneseven+"]";
	}
	
	/**
	 * Internal constructor to create a PerceptionsOfRisk from a  representation.
	 * @param json_data The raw json data that will be parsed.
	 */
    public PerceptionsOfRisk(JSONObject json_data) {
        try {// 26+
            this.twosixplus = ((Number)json_data.get("26+")).doubleValue();// 18-25
            this.oneeightTwofive = ((Number)json_data.get("18-25")).doubleValue();// 12-17
            this.onetwoOneseven = ((Number)json_data.get("12-17")).doubleValue();
        } catch (NullPointerException e) {
    		System.err.println("Could not convert the response to a PerceptionsOfRisk; a field was missing.");
    		e.printStackTrace();
    	} catch (ClassCastException e) {
    		System.err.println("Could not convert the response to a PerceptionsOfRisk; a field had the wrong structure.");
    		e.printStackTrace();
        }
	}	
}