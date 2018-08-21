package com.kefet.image;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import org.apache.log4j.Logger;

/**
 * This class will save a url image coming as a url for example from facebook into a directory 
 * that is assigned to it as a parameter.
 * @author hardddisk
 *
 */

public class SaveImageFromUrl {
	
	private final Logger log = Logger.getLogger(this.getClass());
	
	/**
	 * This method will save a url of image coming for example from facebook url into a directory location and faile name 
	 * assigned to it as a parameter.
	 * @param imageUrl: The url of the image 
	 * @param destinationFile: the destination name and the file location example /home/myhomedir/myimage.jpg
	 * @return boolean value true if it is successful false if not successful.
	 */
	public boolean saveImage(String imageUrl, String destinationFile) {
		
		boolean imageSaved=true;
		InputStream is;
		try {
			URL url = new URL(imageUrl);
			is = url.openStream();
			OutputStream os = new FileOutputStream(destinationFile);

			byte[] b = new byte[2048];
			int length;

			while ((length = is.read(b)) != -1) {
				os.write(b, 0, length);
			}
			is.close();
			os.close();
		} catch (IOException e) {
			imageSaved=false;
			log.error("###################################################");
			log.error("# ERROR SAVING THE IMAGE :"+e);
			log.error("###################################################");
		}
		return imageSaved;
	}
}
