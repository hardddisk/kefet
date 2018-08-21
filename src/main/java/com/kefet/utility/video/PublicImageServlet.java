package com.kefet.utility.video;



import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLConnection;
import java.net.URLDecoder;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import java.util.Iterator;
import java.util.Properties;
import com.kefet.utility.PropertiesLoader;
import javax.servlet.annotation.WebServlet;


/**
 * The Image servlet for serving from absolute path.
 * @author BalusC
 * @link http://balusc.blogspot.com/2007/04/imageservlet.html
 */
@WebServlet(name = "PublicImageServlet", urlPatterns = {"/imgs"})
public class PublicImageServlet extends HttpServlet {
	
	private static Logger log = Logger.getLogger(PublicImageServlet.class);
	private static final String CLASS_NAME = "PublicImageServlet ";
	private String methodNM = null;
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -2084518316404534097L;

        @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		methodNM = CLASS_NAME+".doGet()";
		log.info("in " + methodNM);
		
		Properties props = PropertiesLoader.getProperties();
		
		
		String imageDirectoryLocation=props.getProperty("imageDirectoryLocation");	
		
		
	//	FacesContext facesContext = FacesContext.getCurrentInstance();
   //		  HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
		  
	//	  List videoListCarousel = (List)session.getAttribute("videoListCarousel");
		

        // Get file name from request.
    
        String imgval = request.getParameter("imgval");
       
        
        log.info(CLASS_NAME+"---imgval-----"+imgval);
        
 
        // Check if file name is actually supplied to the request URI.
        if (imgval == null) {
            // Do your thing if the image is not supplied to the request URI.
            // Throw an exception, or send 404, or show default/warning image, or just ignore it.
        	//TODO
        	try{
        		response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
        	}catch(Exception e){	
        		log.error("in " + methodNM+"---Exception---", e);
        	}
            
            return;
        }
        //TODO only when a user provide video URL name the program must generate video location
        // and provide the video for the user.      
        imageDirectoryLocation+=imgval;
         
        // In a Windows environment with the Application server running on the
        // c: volume, the above path is exactly the same as "c:\images".
        // In UNIX, it is just straightforward "/images".
        // If you have stored images in the WebContent of a WAR, for example in the
        // "/WEB-INF/images" folder, then you can retrieve the absolute path by:
        // String imageFilePath = getServletContext().getRealPath("/WEB-INF/images");
                
        File imageFile=null;
        
        // Check if file name is supplied to the request.
        if (imageDirectoryLocation != null) {
            // Strip "../" and "..\" (avoid directory sniffing by hackers!).
        	imageDirectoryLocation = imageDirectoryLocation.replaceAll("\\.+(\\\\|/)", "");
        } else {
            // Do your thing if the file name is not supplied to the request.
            // Throw an exception, or show default/warning image, or just ignore it.
        	log.error("in " + methodNM+"====invalid URL name for an image or it's null======");
            return;
        }
        
     // Check if file name is supplied to the request.
        if (imageDirectoryLocation != null) {
            // Strip "../" and "..\" (avoid directory sniffing by hackers!).
        	imageDirectoryLocation = imageDirectoryLocation.replaceAll("\\.+(\\\\|/)", "");
        } else {
            // Do your thing if the file name is not supplied to the request.
            // Throw an exception, or show default/warning image, or just ignore it.
            return;
        }
        
      //  directoriesLocationThmb=directoriesLocationThmb+videoLocation+"/";
        try{
        	// Decode the file name and prepare file object.
        	imageDirectoryLocation = URLDecoder.decode(imageDirectoryLocation, "UTF-8");
             imageFile = new File(imageDirectoryLocation);
          
        }catch(Exception e){
        	log.error("in " + methodNM+"---Exception---"+e.toString());
        }


        // Check if file actually exists in filesystem.
        if (!imageFile.exists()) {
            // Do your thing if the file appears to be non-existing.
            // Throw an exception, or show default/warning image, or just ignore it.
        	log.error("in " + methodNM+"==========--DO NOT EXIST--============="+imageFile);
            return;
        }
        
        // Get content type by filename.
        String contentType = URLConnection.guessContentTypeFromName(imageDirectoryLocation);
/*
        // Check if file is actually an image (avoid download of other files by hackers!).
        // For all content types, see: http://www.w3schools.com/media/media_mimeref.asp
        if (contentType == null || !contentType.startsWith("image")) {
        	// TODO
            // Do your thing if the file appears not being a real image.
            // Throw an exception, or show default/warning image, or just ignore it.
        	System.out.println("IS NOT AN IMAGE");
            return;
        }      
*/
  
        if(getFormatName(imageFile) == null){
        	// TODO
            // Do your thing if the file appears not being a real image.
            // Throw an exception, or show default/warning image, or just ignore it.
        	log.error("in " + methodNM+"==========--DO NOT EXIST--=============IS NOT AN IMAGE");
        }
        // Prepare streams.
        BufferedInputStream input = null;
        BufferedOutputStream output = null;

        try {
            // Open image file.
            input = new BufferedInputStream(new FileInputStream(imageFile));
            int contentLength = input.available();

            // Init servlet response.
            response.reset();
            response.setContentLength(contentLength);
            response.setContentType(contentType);
            response.setHeader(
                "Content-disposition", "inline; filename=\"" + imageDirectoryLocation + "\"");
            output = new BufferedOutputStream(response.getOutputStream());

            // Write file contents to response.
            while (contentLength-- > 0) {
                output.write(input.read());
            }

            // Finalize task.
            output.flush();
            // this is so the directoriesLocation wont keep incrementing it self
            imageDirectoryLocation=null;
        } catch (IOException e) {
            // Something went wrong?
        	log.error("in " + methodNM+"--1-IOException---", e);
        } finally {
            // Gently close streams.
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    // Do your thing with the exception. Print it, log it or mail it.
                	log.error("in " + methodNM+"--2-IOException---", e);
                }
            }
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                	log.error("in " + methodNM+"--3-IOException---", e);
                    // Do your thing with the exception. Print it, log it or mail it.           
                }
            }
        }
    }
	
    //**************************************************************************************************
    
    // Returns the format name of the image in the object 'o'.
    // 'o' can be either a File or InputStream object.
    // Returns null if the format is not known.
    private static String getFormatName(Object o) {
    	String methodNMi = CLASS_NAME+".getFormatName()";
    	log.info("in " + methodNMi);
    	
        try {
            // Create an image input stream on the image
            ImageInputStream iis = ImageIO.createImageInputStream(o);
    
            // Find all image readers that recognize the image format
            Iterator iter = ImageIO.getImageReaders(iis);
            if (!iter.hasNext()) {
                // No readers found
                return null;
            }
             
            // Use the first reader
            ImageReader reader = (ImageReader)iter.next();
    
            // Close stream
            iis.close();
            
            return reader.getFormatName();
        } catch (IOException e) {
        	// TODO print in log file
        	log.error("in " + methodNMi+"---IOException---", e);
        }
        // The image could not be read
        return null;
    }
}