/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kefet.utility;

import com.google.api.services.youtube.YouTube;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jsoup.Jsoup;
import org.json.simple.JSONObject;

/**
 * this class is built to read the from the google api what needed to be examined
 * for local use.
 * @author hardddisk
 */
public class YouTubeAPIV3Reader {
    
    // https://www.googleapis.com/youtube/v3/videos?id=7lCDEYXw3mM&key=YOUR_API_KEY&part=snippet,contentDetails,statistics,status
    
    // example of api
    // https://developers.google.com/youtube/v3/getting-started
    // https://developers.google.com/youtube/v3/code_samples/java
    
    private static YouTube youtube;
    
    private String videoId;
    private String duration;
    private final String PRIVATE_DEVELOPER_KEY="AIzaSyBRHVwIoVIc3VQzBvmaG0AOIXjZJSmDDIs";
    
    // coma separeted url that we want to get from google API in this case we need only the duration which is saved in contentDetails attribute.
    private final String PART="contentDetails";
    
    public YouTubeAPIV3Reader(String videoId){
        this.videoId = videoId;
    }
    
    public String getDurationForHuman(){
        
        
        String url = "https://www.googleapis.com/youtube/v3/videos?id=mpzIQt6l4xY&key="+PRIVATE_DEVELOPER_KEY+"&part=contentDetails";
        
        Object obj = null;
        try {
             obj = Jsoup.connect(url).timeout(10 * 1000).get();
        } catch (IOException ex) {
            Logger.getLogger(YouTubeAPIV3Reader.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        JSONObject jsonObject = (JSONObject) obj;
        System.out.println(jsonObject);
        String duration = (String) jsonObject.get("duration");
        System.out.println(duration);
        

        return "";
    }
    
}
