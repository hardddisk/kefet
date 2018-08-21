package com.kefet.utility;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;

public class CropImage {
	
	
	private final Logger log = Logger.getLogger(this.getClass());
	
	/**
	 * This method will crop an image from the top part. 
	 * @param imageFullPath
	 * @param heightToBeCropped from the top part 
	 * @param storageLocation the name of the Cover Image that will be saved
	 * @return boolean value if the cropping and storing of the image is successful.
	 */
	public boolean cropImageTopPart(String imageFullPath, String storageLocation, int heightToBeCropped){
		
		boolean success=true;
		
		try{
			BufferedImage original = ImageIO.read(new File(imageFullPath));
			 int width = original.getWidth();
			  int height = original.getHeight();
			 BufferedImage out = original.getSubimage(0, Math.abs(heightToBeCropped), width, height-Math.abs(heightToBeCropped));
			 File f = new File(storageLocation);
			 ImageIO.write(out, "JPEG", f);
			}catch(IOException e){
				success=false;
				log.error("#####################################################");
				log.error("# IOException: "+e);
				log.error("#####################################################");
			}
		
		return success;
	}
	
	/**
	 *  This method will crop an image from the bottom part.
	 * @param imageFullPath
	 * @param storageLocation the name of the Cover Image that will be saved
	 * @param heightToBeCropped
	 * @return boolean value if the cropping and storing of the image is successful.
	 */
    public boolean cropImageBottomPart(String imageFullPath, String storageLocation, int heightToBeCropped){
		boolean success=true;
		
		try{
			BufferedImage original = ImageIO.read(new File(imageFullPath));
			 int width = original.getWidth();
			  int height = original.getHeight();
			  height=700;
			  if(height > 315 && heightToBeCropped==0){
				  System.out.println("OKKEEYYY");
				  heightToBeCropped= height - 315;
			  }
			 BufferedImage out = original.getSubimage(0, 0, width, height-Math.abs(heightToBeCropped));
			 File f = new File(storageLocation);
			 ImageIO.write(out, "JPEG", f);
			}catch(IOException e){
				success=false;
				log.error("#####################################################");
				log.error("# IOException: "+e);
				log.error("#####################################################");
			}
		
		return success;
	}

}
