package com.kefet.utility.directories;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

@Transactional(readOnly = true)
@Service
public class DirectoryCreationImpl implements DirectoryCreation {
	
	private final Logger log = Logger.getLogger(this.getClass());
	
	@Override
	public int getLastDirectory(String directoryLocation){
		Path dir = Paths.get(directoryLocation); 
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {	
			
			SortedSet<Integer> set = new TreeSet<Integer>();			
				
			for (Path file: stream) {
			    set.add(Integer.parseInt(file.getFileName().toString()));
			}
			return Integer.parseInt(set.last().toString());
		} catch (IOException | DirectoryIteratorException e) {
                    log.error("###############################################");
                    log.error("#unable to get last directory                 #"+e);
                    log.error("###############################################");
		}
			return 0;
	}

	@Override
	public boolean createDirectory(String directoryLocation,
			String newDirectoryName) {
		
		boolean directoryCreated=true;
		directoryLocation+="/"+newDirectoryName;
		Path testDirectoryPath  = Paths.get(directoryLocation);
		
            try {
			Files.createDirectories(testDirectoryPath);
		} catch (IOException e) {
		    directoryCreated=false;
	            log.error("###############################################");
                    log.error("#unable to create directory                   #"+e);
                    log.error("###############################################");	
		}
		return directoryCreated;
	}
	
	
	@Override
	public boolean createDirectory(String absolutePath) {
		
		boolean directoryCreated=true;
		
		Path testDirectoryPath  = Paths.get(absolutePath);
		
        try {
			Files.createDirectories(testDirectoryPath);
		} catch (IOException e) {
			directoryCreated=false;
                    log.error("###############################################");
                    log.error("#unable to create directory                   #"+e);
                    log.error("###############################################");
		}
		return directoryCreated;
	}
	
	@Override
	public boolean createDirectoryWithThumb(String directoryLocation,
			String newDirectoryName) {
		boolean directoryCreated=true;
		
		directoryLocation+="/"+newDirectoryName+"/thumb_";	
		Path testDirectoryPath  = Paths.get(directoryLocation);
		
            try {
                    Files.createDirectories(testDirectoryPath);
		} catch (IOException e) {
			directoryCreated=false;
			
                    log.error("###############################################");
                    log.error("#unable to create directory with thumb        #"+e);
                    log.error("###############################################");
		}
		return directoryCreated;
	}
	
	@Override
	public long numberOfContentInADir(String directoryLocation) {
		
		log.debug("==========================");
		log.debug("==========================");
		log.debug("============directoryLocation=============="+directoryLocation);
		log.debug("==========================");
		log.debug("==========================");
		
		File dir = new File(directoryLocation);	
		
		return dir.list().length;
	}

	@Override
	public boolean isDirectory(String directoryLocation) {
		return Files.isDirectory(Paths.get(directoryLocation));
	}
	
	@Override
	public boolean deleteFile(String filePath){
		boolean success =true;
		
		Path target = Paths.get(filePath);
		try {
			Files.delete(target);
		} catch (IOException e) {
			success = false; 
			log.error("##########################################################################");
			log.error("# was not able to delete the file"+filePath);
			log.error("##########################################################################");
		}
		return success;
	}

    @Override
    public boolean saveImageFileToLocalDisk(MultipartFile multipartFile, String directoryLocation) {
        boolean fileSaved=true;
      
        try {
                FileCopyUtils.copy(multipartFile.getBytes(), new FileOutputStream(directoryLocation));
        } catch (IOException e) {
            fileSaved= false;
            log.error("##########################################################################");
            log.error("# was not able to save the file"+directoryLocation+"######################"+e);
            log.error("##########################################################################");	
        }
        return fileSaved;
        
    }
        
   


}
