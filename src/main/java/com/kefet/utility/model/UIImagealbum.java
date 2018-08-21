package com.kefet.utility.model;

import java.util.HashSet;
import java.util.Set;

import com.kefet.model.Image;
import com.kefet.model.ImageImagealbum;

public class UIImagealbum {
	private String albumName;
	private Set<ImageImagealbum> imageImagealbums = new HashSet<ImageImagealbum>(
			0);
	private Set<Image> images = new HashSet<Image>(0);
	
	
	public String getAlbumName() {
		return albumName;
	}
	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}
	public Set<ImageImagealbum> getImageImagealbums() {
		return imageImagealbums;
	}
	public void setImageImagealbums(Set<ImageImagealbum> imageImagealbums) {
		this.imageImagealbums = imageImagealbums;
	}
	public Set<Image> getImages() {
		return images;
	}
	public void setImages(Set<Image> images) {
		this.images = images;
	}

}
