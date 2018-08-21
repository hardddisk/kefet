package com.kefet.utility.video;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

public class ThumbnailPostionCalculator implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private static final Logger log = Logger.getLogger(ThumbnailPostionCalculator.class);
	
	
	private long microSecondForThumbnail;
	
	public long getMicroSecondForThumbnail() {
		return microSecondForThumbnail;
	}
	public void setMicroSecondForThumbnail(long microSecondForThumbnail) {
		this.microSecondForThumbnail = microSecondForThumbnail;
	}
	
	public long calculateMicrosecond(String thumbnailInHours){
		long thumbnailPosition=0;
		if(thumbnailInHours != null){
			if(!thumbnailInHours.trim().equals("")){
				String[] timeHolder = thumbnailInHours.split(":");
				long hours=TimeUnit.HOURS.toSeconds(Integer.parseInt(timeHolder[0]));
				long minutes=TimeUnit.MINUTES.toSeconds(Integer.parseInt(timeHolder[1]));
				long seconds=TimeUnit.SECONDS.toSeconds(Integer.parseInt(timeHolder[2]));
			
				thumbnailPosition = (hours + minutes + seconds);
			}
		}
		
		return thumbnailPosition;
	}
	 

}
