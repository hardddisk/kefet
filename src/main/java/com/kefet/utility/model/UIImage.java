package com.kefet.utility.model;


import com.kefet.model.Imagealbum;
import com.kefet.model.Users;

public class UIImage {
	
	private Integer id;
	private Users users;
	private Imagealbum imagealbum;
	private String albumName;
	private String albumDirLocation;
	private String imgTittle;
	private String imageDescription;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Users getUsers() {
		return users;
	}
	public void setUsers(Users users) {
		this.users = users;
	}
	
	public String getAlbumName() {
		return albumName;
	}
	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}
	
	public String getAlbumDirLocation() {
		return albumDirLocation;
	}
	public void setAlbumDirLocation(String albumDirLocation) {
		this.albumDirLocation = albumDirLocation;
	}
	
	public Imagealbum getImagealbum() {
		return imagealbum;
	}
	public void setImagealbum(Imagealbum imagealbum) {
		this.imagealbum = imagealbum;
	}
	public String getImgTittle() {
		return imgTittle;
	}
	public void setImgTittle(String imgTittle) {
		this.imgTittle = imgTittle;
	}
	public String getImageDescription() {
		return imageDescription;
	}
	public void setImageDescription(String imageDescription) {
		this.imageDescription = imageDescription;
	}
}
