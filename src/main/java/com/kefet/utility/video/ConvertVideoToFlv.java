package com.kefet.utility.video;


import java.io.File;
import java.io.Serializable;
import java.util.Properties;


import org.apache.log4j.Logger;

import com.kefet.utility.PropertiesLoader;

//import com.xuggle.mediatool.IMediaReader;
//import com.xuggle.mediatool.ToolFactory;
//import com.xuggle.xuggler.IContainer;


public class ConvertVideoToFlv implements Serializable {
	
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Logger log = Logger.getLogger(ConvertVideoToFlv.class);
	  private static final String CLASS_NAME = "ConvertVideoToFlv ";
	  private String methodNM = null;
	  
	  
		
		private String videoUrlName;
		private String videoLocation;
		private String fullPathThumbnailLocation;
		private String fullPathVideoLocation;

		public String getFullPathThumbnailLocation() {
			return fullPathThumbnailLocation;
		}
		public void setFullPathThumbnailLocation(String fullPathThumbnailLocation) {
			this.fullPathThumbnailLocation = fullPathThumbnailLocation;
		}
		
		public String getFullPathVideoLocation() {
			return fullPathVideoLocation;
		}
		public void setFullPathVideoLocation(String fullPathVideoLocation) {
			this.fullPathVideoLocation = fullPathVideoLocation;
		}
		
		public String getVideoUrlName() {
			return videoUrlName;
		}
		public void setVideoUrlName(String videoUrlName) {
			this.videoUrlName = videoUrlName;
		}
		public String getVideoLocation() {
			return videoLocation;
		}
		public void setVideoLocation(String videoLocation) {
			this.videoLocation = videoLocation;
		}
		
		//***************************************************************************************
/*		
		public boolean convertVideo(String absolutePath, String videoUrlName, long thumbnailCreationTime){
			methodNM = CLASS_NAME+".convertVideo()";
			log.info("in " + methodNM);
			   
			// create a Xuggler container object
	        IContainer container = IContainer.make();
	        
	        // open up the container
	        if (container.open(absolutePath, IContainer.Type.READ, null) < 0){
	      	  log.error("---could not open file ->" + absolutePath);		    
	        }
	        
	        // this will create a time where the thumbnail will be created. 
	        // if the user didn't enter anything it should be equal to 0
	   
	        if(thumbnailCreationTime == 0){
	        	thumbnailCreationTime = createThumbnailTime(container.getDuration());
	        }
	        
	        
	        getVideoLocationFromAbsolutePath(absolutePath);
	        
	    	long watermark = container.getDuration()/1000; // converting to millisecond
	    	System.out.println("in " + methodNM+"~~~~~~~~absolutePath~~~~~~~~~~~~~~~~~~~~~~~~~~~"+absolutePath);
	    	System.out.println("in " + methodNM+"~~~~~~~~videoUrlName~~~~~~~~~~~~~~~~~~~~~~~~~~~"+videoUrlName);
	    	System.out.println("in " + methodNM+"~~~~~~~~watermark~~~~~~~~~~~~~~~~~~~~~~~~~~~"+watermark);
	    	System.out.println("in " + methodNM+"~~~~~~~~videoLocation~~~~~~~~~~~~~~~~~~~~"+videoLocation);
	    	System.out.println("in " + methodNM+"~~~~~~~~container.getDuration()~~~~~~~~~~~~~"+container.getDuration());
	    	System.out.println("in " + methodNM+"~~~~~~~~thumbnailCreationTime~~~~~~~~~~~~~~~"+thumbnailCreationTime);
	    	
	    	// create a media reader
	    	IMediaReader mediaReader = ToolFactory.makeReader(absolutePath);
	    	
	    	// stipulate that we want BufferedImages created in BGR 24bit color space
	        mediaReader.setBufferedImageTypeToGenerate(BufferedImage.TYPE_3BYTE_BGR);
	        
	        ImageSnapListener  imageSnapListener = new ImageSnapListener();	 
	        
	        // TODO make sure imageSnapListener.setWaterMark(container.getDuration()/1000000); is checked.
	        
	        
	        // thumbnail location must be similar place as videoLocation but the root  
	        // directory of the thumbnail is inside tomcat/webapp where the application is located.
	        String thumbnailDirectoryLocation=getThumbnailDirectoryLocation(absolutePath);
	        DirectoryCreation directoryCreation = new DirectoryCreationImpl();
	        directoryCreation.createDirectory(thumbnailDirectoryLocation);
	        
	        imageSnapListener.setThumbnailLocation(thumbnailDirectoryLocation);

	        imageSnapListener.setVideoUrlName(videoUrlName);
	        imageSnapListener.setThumbnailCreationTime(thumbnailCreationTime);
	        imageSnapListener.setWaterMark(watermark);
	    	 // add a viewer to the reader, to see the decoded media
	        mediaReader.addListener(ToolFactory.makeWriter(videoLocation+"/"+videoUrlName+".mp4", mediaReader));
	        
	        if(!imageSnapListener.isImageCreated()){
	        	
	        	mediaReader.addListener(imageSnapListener);
	        	
	        }
	        
	    	// read and decode packets from the source file and
	    	// and dispatch decoded audio and video to the writer
	    	while (mediaReader.readPacket() == null);  		    	 
	    	 
	    	 
	    	 // change the file from "bla.flv" to "bla" only 
	    	// String name=videoLocation+"/"+videoUrlName.replaceAll(".flv","" );
	    	 
	    	//return renameFile(videoLocation+"/"+videoUrlName+".flv", name);
        	this.fullPathThumbnailLocation = imageSnapListener.getFullPathThumbnailLocation();
        	this.fullPathVideoLocation = videoLocation+"/"+videoUrlName+".mp4";
        	
                return true;
		}
		
		//***************************************************************************************
		
		
		public boolean renameFile(String file, String toFile) {
			methodNM = CLASS_NAME+".renameFile()";
			log.info("in " + methodNM);
			
			boolean fileRenamed=true;

			File toBeRenamed = new File(file);
			if (!toBeRenamed.exists() || toBeRenamed.isDirectory()) {
				log.error(methodNM+"---File does not exist:---"+ file);	
				fileRenamed= false;
			}
			File newFile = new File(toFile);
			//Rename
			if (toBeRenamed.renameTo(newFile)) {
				log.info(methodNM+"---File has been renamed.---");
			} else {
				log.error(methodNM+"---Error renmaing file---");
				fileRenamed=false;
			}
			
			return fileRenamed;
		} 
		
		//**********************************************************************************************************
		
		// this method return random number between 1 to 4 
		private int createRandomNumber(){
			  Random generator = new Random();
			  return generator.nextInt(6) + 1;
		}
		
		//**********************************************************************************************************
		
		private long createThumbnailTime(long duration){
			
			long thumbnailCreationTime=0;
			if(duration >= 1000000){
	    		thumbnailCreationTime = duration /1000000;
	    	}
	    	return  thumbnailCreationTime / createRandomNumber();	
		}
		
		//**********************************************************************************************************
		
		/**
		 * 
		 * @param fullPath: exmple /home/myhome/videoUrlName.mp4
		 * @return return: only  videoUrlName from the fullPath
		 */
		
		public String getVideoUrlNameFromAbsolutePath(String fullPath){
			String VideoUrlName = new File(fullPath).getName();
			if(VideoUrlName.contains(".")){
				VideoUrlName = VideoUrlName.substring(0, VideoUrlName.lastIndexOf('.'));
			}
			
			
			return VideoUrlName;
		}
		
		/**
		 * 
		 * @param absolutePath: exmple /home/myhome/videoUrlName.mp4
		 * @return return: /home/myhome/
		 */
		
		 public String getVideoLocationFromAbsolutePath(String absolutePath){
			this.videoLocation = absolutePath.
   	    	     substring(0,absolutePath.lastIndexOf(File.separator));
			return videoLocation;
		}
		
		/**
		 * TODO wirte comment 
		 * @param videoLocation
		 * @return
		 */
		
		public String getThumbnailDirectoryLocation(String videoLocation){
			Properties props = PropertiesLoader.getProperties();
			
			String directoriesLocationThmb = props.getProperty("directoriesLocationThmb");
			String thumbLocationDir=videoLocation.substring(videoLocation.lastIndexOf("users/")+6);
			return directoriesLocationThmb+=thumbLocationDir;
		}
}
