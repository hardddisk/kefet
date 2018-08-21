package com.kefet.utility.video;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public class GetVideoAndThumbnail {
	
	MultipartFile video;
	MultipartFile image;
	
	
	public GetVideoAndThumbnail(Map<String, MultipartFile> fileMap){
		if(fileMap != null){
			if(fileMap.size() == 2){
				for(MultipartFile multipartFile : fileMap.values()){
					if(multipartFile.getContentType().contains("image/")){
						//System.out.println("I AM AN IMAGE");
						this.image = multipartFile;
					}if(multipartFile.getContentType().contains("video/")){
						//System.out.println("I AM A VIDEO");
						this.video = multipartFile;
					}
				}
			}else if(fileMap.size() == 1){
				for(MultipartFile multipartFile : fileMap.values()){
					//System.out.println("IN SIDE A SINGLE FILE");
					if(multipartFile.getContentType().contains("video/")){
					//	System.out.println("I AM A VIDEO");
						this.video = multipartFile;
					}
				}
			}
		}
	}

	public MultipartFile getVideo() {
		return video;
	}


	public void setVideo(MultipartFile video) {
		this.video = video;
	}


	public MultipartFile getImage() {
		return image;
	}


	public void setImage(MultipartFile image) {
		this.image = image;
	}

}
