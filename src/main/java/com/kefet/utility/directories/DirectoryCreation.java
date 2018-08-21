package com.kefet.utility.directories;

import org.springframework.web.multipart.MultipartFile;

public interface DirectoryCreation {
	
	/**
	 * 
	 * This method return the last number of the directory existing in a specific path
	 * all the directory usually are created by a number. if we have 1 to 10 directories 
	 * this method will return 10. 
	 * @param directoryLocation : path of the directory location 
	 * @return integer value of the directory.
	 */
	public int getLastDirectory(String directoryLocation);
	
	/**
	 * This method will create a new directory in a directoryPath assigned by 'directoryLocation' parameter.
	 * @param directoryLocation: The location where the new directory will be created
	 * @param newDirectoryName: The name of the new directory.
	 * @return true if the directory was created otherwise false.
	 */
	public boolean createDirectory(String directoryLocation, String newDirectoryName);
	
	/**
	 * This method will create a directory by reading the 'absolutePath' parameter. 
	 * @param absolutePath: The directory where it will be created.
	 * @return true if the directory was created otherwise false.
	 */
	public boolean createDirectory(String absolutePath);
	
	public boolean createDirectoryWithThumb(String directoryLocation, String newDirectoryName);
	
	/**
	 * This method will read how many file does exist in a directory and return the number.
	 * @param directoryLocation: the location where the direcotry needs to read how many file exists.
	 * @return number of files that exists.
	 */
	public long numberOfContentInADir(String directoryLocation);
	
	/**
	 * This method will return the last name of a directory location if it is a file or a directory. 
	 * ex: in linux case /home will be a directory/ it will return true. if /home/username/mytext.txt, 
	 * mytext.txt will be false for it is not a directory
	 * @param directoryLocation: path of the directory in String format 
	 * @return boolean value
	 */
	public boolean isDirectory(String directoryLocation);
	
	/**
	 * This method will delete a file. if successful will return true.
	 * @param filePath: path and the file needed to be deleted.
	 * @return boolean value true or false. True if successful.
	 */
	public boolean deleteFile(String filePath);
        
        /**
         * This method will save a MultipartFile object to a directory location, and returns the full path of the file.
         * @param multipartFile  the object that needs to be saved.
         * @param directoryLocation the location of the directory.
         * @return the boolean value if false that means the file wasn't saved and we must check the log statement.
         */
         boolean saveImageFileToLocalDisk(MultipartFile multipartFile, String directoryLocation);
	
	
	
}
