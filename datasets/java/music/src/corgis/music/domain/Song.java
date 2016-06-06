package corgis.music.domain;

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
public class Song {
	
    private Double key;
    private Double mode_Confidence;
    private Double artist_Mbtags_Count;
    private Double key_Confidence;
    private Double tatums_Start;
    private Long year;
    private Double duration;
    private Double hotttnesss;
    private Double beats_Start;
    private Double time_Signature_Confidence;
    private String title;
    private Double bars_Confidence;
    private String id;
    private Double bars_Start;
    private String artist_Mbtags;
    private Double start_Of_Fade_Out;
    private Double tempo;
    private Double end_Of_Fade_In;
    private Double beats_Confidence;
    private Double tatums_Confidence;
    private Long mode;
    private Double time_Signature;
    private Double loudness;
    
    
    /*
     * @return 
     */
    public Double getKey() {
        return this.key;
    }
    
    
    
    /*
     * @return 
     */
    public Double getMode_Confidence() {
        return this.mode_Confidence;
    }
    
    
    
    /*
     * @return 
     */
    public Double getArtist_Mbtags_Count() {
        return this.artist_Mbtags_Count;
    }
    
    
    
    /*
     * @return 
     */
    public Double getKey_Confidence() {
        return this.key_Confidence;
    }
    
    
    
    /*
     * @return 
     */
    public Double getTatums_Start() {
        return this.tatums_Start;
    }
    
    
    
    /*
     * @return 
     */
    public Long getYear() {
        return this.year;
    }
    
    
    
    /*
     * @return 
     */
    public Double getDuration() {
        return this.duration;
    }
    
    
    
    /*
     * @return 
     */
    public Double getHotttnesss() {
        return this.hotttnesss;
    }
    
    
    
    /*
     * @return 
     */
    public Double getBeats_Start() {
        return this.beats_Start;
    }
    
    
    
    /*
     * @return 
     */
    public Double getTime_Signature_Confidence() {
        return this.time_Signature_Confidence;
    }
    
    
    
    /*
     * @return 
     */
    public String getTitle() {
        return this.title;
    }
    
    
    
    /*
     * @return 
     */
    public Double getBars_Confidence() {
        return this.bars_Confidence;
    }
    
    
    
    /*
     * @return 
     */
    public String getId() {
        return this.id;
    }
    
    
    
    /*
     * @return 
     */
    public Double getBars_Start() {
        return this.bars_Start;
    }
    
    
    
    /*
     * @return 
     */
    public String getArtist_Mbtags() {
        return this.artist_Mbtags;
    }
    
    
    
    /*
     * @return 
     */
    public Double getStart_Of_Fade_Out() {
        return this.start_Of_Fade_Out;
    }
    
    
    
    /*
     * @return 
     */
    public Double getTempo() {
        return this.tempo;
    }
    
    
    
    /*
     * @return 
     */
    public Double getEnd_Of_Fade_In() {
        return this.end_Of_Fade_In;
    }
    
    
    
    /*
     * @return 
     */
    public Double getBeats_Confidence() {
        return this.beats_Confidence;
    }
    
    
    
    /*
     * @return 
     */
    public Double getTatums_Confidence() {
        return this.tatums_Confidence;
    }
    
    
    
    /*
     * @return 
     */
    public Long getMode() {
        return this.mode;
    }
    
    
    
    /*
     * @return 
     */
    public Double getTime_Signature() {
        return this.time_Signature;
    }
    
    
    
    /*
     * @return 
     */
    public Double getLoudness() {
        return this.loudness;
    }
    
    
    
	
	/**
	 * Creates a string based representation of this Song.
	
	 * @return String
	 */
	public String toString() {
		return "Song[" +key+", "+mode_Confidence+", "+artist_Mbtags_Count+", "+key_Confidence+", "+tatums_Start+", "+year+", "+duration+", "+hotttnesss+", "+beats_Start+", "+time_Signature_Confidence+", "+title+", "+bars_Confidence+", "+id+", "+bars_Start+", "+artist_Mbtags+", "+start_Of_Fade_Out+", "+tempo+", "+end_Of_Fade_In+", "+beats_Confidence+", "+tatums_Confidence+", "+mode+", "+time_Signature+", "+loudness+"]";
	}
	
	/**
	 * Internal constructor to create a Song from a  representation.
	 * @param map The raw json data that will be parsed.
	 * @return 
	 */
    public Song(JSONObject json_data) {
        try {
            this.key = (Double)json_data.get("key");
            this.mode_Confidence = (Double)json_data.get("mode_confidence");
            this.artist_Mbtags_Count = (Double)json_data.get("artist_mbtags_count");
            this.key_Confidence = (Double)json_data.get("key_confidence");
            this.tatums_Start = (Double)json_data.get("tatums_start");
            this.year = (Long)json_data.get("year");
            this.duration = (Double)json_data.get("duration");
            this.hotttnesss = (Double)json_data.get("hotttnesss");
            this.beats_Start = (Double)json_data.get("beats_start");
            this.time_Signature_Confidence = (Double)json_data.get("time_signature_confidence");
            this.title = (String)json_data.get("title");
            this.bars_Confidence = (Double)json_data.get("bars_confidence");
            this.id = (String)json_data.get("id");
            this.bars_Start = (Double)json_data.get("bars_start");
            this.artist_Mbtags = (String)json_data.get("artist_mbtags");
            this.start_Of_Fade_Out = (Double)json_data.get("start_of_fade_out");
            this.tempo = (Double)json_data.get("tempo");
            this.end_Of_Fade_In = (Double)json_data.get("end_of_fade_in");
            this.beats_Confidence = (Double)json_data.get("beats_confidence");
            this.tatums_Confidence = (Double)json_data.get("tatums_confidence");
            this.mode = (Long)json_data.get("mode");
            this.time_Signature = (Double)json_data.get("time_signature");
            this.loudness = (Double)json_data.get("loudness");
        } catch (NullPointerException e) {
    		System.err.println("Could not convert the response to a Song; a field was missing.");
    		e.printStackTrace();
    	} catch (ClassCastException e) {
    		System.err.println("Could not convert the response to a Song; a field had the wrong structure.");
    		e.printStackTrace();
        }
	}	
}