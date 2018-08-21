package com.kefet.utility.directories;

import org.apache.log4j.Logger;


public class UserDirectoryCreation {
	
	private static final Logger log = Logger.getLogger(UserDirectoryCreation.class);
	private static final String CLASS_NAME = "UserDirectoryCreation ";
	private String methodNM = null;

    DirectoryCreation directoryCreationImpl = new DirectoryCreationImpl();
	
	
	//****************************************************************************
	
	public boolean isUserHomeDirectoryExist(String id, String directoryLocation){
		methodNM = CLASS_NAME+".isUserHomeDirectoryExist()";
		log.info("in " + methodNM);
		
		log.debug("in " + methodNM+"id==="+id);
		
		log.debug("!!!!!!!!!!!!!!!!!!!!!!!"+directoryLocation+id);
		
		return directoryCreationImpl.isDirectory(directoryLocation+id);
	}
	
	//****************************************************************************
	
	public int getLastDirecotryUsedToStoreVideo(String id, String directoryLocation){
		return directoryCreationImpl.getLastDirectory(directoryLocation+"/"+id);	
	}
	
	//****************************************************************************
	
	public long getNumberOfDirectoryContent(String id, int dirName, String directoryLocation){
            methodNM = CLASS_NAME+".isUserHomeDirectoryExist()";
	    log.info("in " + methodNM+"-------"+directoryLocation+"/"+id+"/"+dirName);
            log.debug("in " + methodNM+"-------"+directoryLocation+"/"+id+"/"+dirName);
	    return directoryCreationImpl.numberOfContentInADir(directoryLocation+"/"+id+"/"+dirName);
	}
	
	//****************************************************************************
	
	/*
	public boolean isUserBaseDirectoryExist(String id){
		methodNM = CLASS_NAME+".isUserBaseDirectoryExist()";
		log.info("in " + methodNM);
		
		log.info("in " + methodNM+"id==="+id);
		File file=new File(directoriesLocation+getBaseDirectoryName(id));	
		log.info("in " + methodNM+"file.exists()==="+file.exists());
		
		return file.exists();		
	}
	*/
	
	
	
	
	//****************************************************************************

	/*
	public boolean createUserHomeDirecotry(String id){
		  methodNM = CLASS_NAME+".createUserHomeDirecotry()";
		  log.info("in " + methodNM);
		  
		  boolean success=true;		  
		  String homeDirectory =directoriesLocation+getBaseDirectoryName(id)+"/"+id;
		  
		  log.info("in " + methodNM+"homeDirectory==="+homeDirectory);
		  
		  try{	 
	     		// Create a directory
	     		success = (new File(homeDirectory)).mkdir();

	     }catch (Exception e){//Catch exception if any
	    	 success=false;
	    	 log.error("in..."+methodNM+"---exception---"+ e.getMessage());
	     }
	     log.info("in " + methodNM+"success==="+success);
		return success;		
	}
	*/

	//****************************************************************************
	

	
	public boolean createDirectoryWithThumb(String id, String newDirectoryName, String directoryLocation ){
		return directoryCreationImpl.createDirectoryWithThumb(directoryLocation+"/"+id+"/", newDirectoryName);
	}
	
	
	//****************************************************************************
	
	/*
	
	// this method returns true if the root directory exist.
	public boolean createBaseDirecotry(String baseDirectory){
		  methodNM = CLASS_NAME+".createBaseDirecotry()";
		  log.info("in " + methodNM);
		  
		  boolean success=false;		  
		  String tempBaseDirectory =directoriesLocation+baseDirectory;
		  
		  log.info("in " + methodNM+"tempBaseDirectory ="+tempBaseDirectory);
		  
		  try{	 
	     		// Create a directory
	     		success = (new File(tempBaseDirectory)).mkdir();
	
	     }catch (Exception e){//Catch exception if any
	    	 log.error("in..."+methodNM+"----exception---"+ e.getMessage());
	     }
	     log.info("in " + methodNM+"success ="+success);
		return success;
	}
	
	*/
	
	//****************************************************************************
	
	public String getBaseDirectoryName(String myId){
		  methodNM = CLASS_NAME+".getBaseDirectoryName()";
		  log.info("in " + methodNM);
		  
		  int id=Integer.parseInt(myId);
		  
		  int m = (id/10)+1;
	      int rounded = m*10;
	      int firstNum = rounded - 9;
	      String baseDirectory = firstNum +"-"+rounded;
	      
	      log.info("in..."+methodNM+"----baseDirectory=---"+baseDirectory);
	      
	      return baseDirectory;
	}
	
	//****************************************************************************

}