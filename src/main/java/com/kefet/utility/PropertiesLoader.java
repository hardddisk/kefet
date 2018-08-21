package com.kefet.utility;

import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;

import org.apache.log4j.Logger;


public class PropertiesLoader implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static Logger log = Logger.getLogger(PropertiesLoader.class);
	private static final String CLASS_NAME = "PropertiesLoader ";

	private static Properties props;
	private static final String propertiesFile = "/kefet.properties";
	
	public static Properties getProperties() {
		String methodNM = CLASS_NAME+".getProperties()";
		  log.info("in " + methodNM);	
		
		if(props != null){
			return props;
		}else {
			try {
				InputStream is = PropertiesLoader.class.getResource(propertiesFile).openStream();
				if(is == null){
					log.error("in " + methodNM+"---if Input Strean is ---"+is);
				}
				
				Properties props = new Properties();
				props.load(is);
				is.close();
				return props;
			} catch(Exception e) {
				log.error("in " + methodNM+"---Exception---", e);
				throw new RuntimeException("Could not load properties from " + propertiesFile, e);
			}
			
			
		}
	}
}