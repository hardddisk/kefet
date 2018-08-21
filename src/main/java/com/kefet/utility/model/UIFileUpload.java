package com.kefet.utility.model;

public class UIFileUpload {
	
	private String vidOrImage;
	private String category;
	private String description;
	
	// Video
	private String videoName;	
	// image
    private String albumName;
	
	public String getVidOrImage() {
		return vidOrImage;
	}
	public void setVidOrImage(String vidOrImage) {
		this.vidOrImage = vidOrImage;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getVideoName() {
		return videoName;
	}
	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}
	public String getAlbumName() {
		return albumName;
	}
	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}

}
