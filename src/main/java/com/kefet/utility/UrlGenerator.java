package com.kefet.utility;

import java.util.Properties;



import org.apache.log4j.Logger;


public class UrlGenerator  {
	
	private static Logger log = Logger.getLogger(UrlGenerator.class);
	private static final String CLASS_NAME = "UrlGenerator ";
	private String methodNM = null;
	
	private Properties props = PropertiesLoader.getProperties();
	private String urlToPrintLength=props.getProperty("urlToPrintLength");
	
	private String elegibleChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz123456789_";
	private char[] chars = elegibleChars.toCharArray();
	
	private StringBuffer finalString;
	
	
	
	public String getNewURL(){
		methodNM = CLASS_NAME+".getVideoURL()";
		log.info("in " + methodNM);
		
		System.out.println("$$$$$$$$$$$$$$$$$urlToPrintLength$$$$$$$$$$$"+urlToPrintLength);
		
		  finalString = new StringBuffer();
			for( int i = 0; i < Integer.parseInt(urlToPrintLength); i++ ) {
			     double randomValue = Math.random();
			     int randomIndex = (int) Math.round(randomValue * (chars.length - 1));
			     char characterToShow = chars[randomIndex];
			     
			     finalString.append(characterToShow);
			}
			return finalString.toString();
	}
	
	public String generateRandom5Numbers(){
		String finalString="";
		 String elegibleNumber= "1234567890";
		 char[] eligibleChars = elegibleNumber.toCharArray();
		 
		 for( int i = 0; i < 5; i++ ) {
		     double randomValue = Math.random();
		     int randomIndex = (int) Math.round(randomValue * (eligibleChars.length - 1));
		     char characterToShow = eligibleChars[randomIndex];
		     
		     finalString+=characterToShow;
		}
		return finalString;
	}
}