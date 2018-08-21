package com.kefet.utility.model;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.kefet.model.Users;
import com.kefet.model.Videocategory;
import com.kefet.service.VideocategoryService;

public class UIVideo  implements java.io.Serializable{
	
	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Users users;
	private String videoTitleAmharic;
	private String videoDescripAmharic;
        private String videoTitleEnglish;
        private String videoDescripEnglish;
	private String videoUrlname;
	private String videoLocation;
	private String videoThumbnail;
	private String thumbnailInHours;
        private Boolean videoSpecial; // post video to the main page header.
	private List<Videocategory> videocategory;
       
	
	@Autowired
	VideocategoryService videocategoryService;
	
	String [] selectedVideocategory;
	 
	public String[] getSelectedVideocategory() {
		return selectedVideocategory;
	}
	public void setSelectedVideocategory(String[] selectedVideocategory) {
		this.selectedVideocategory = selectedVideocategory;
	}
	
	private Long size;
	private String type;
	
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
	public String getVideoTitleAmharic() {
		return videoTitleAmharic;
	}
	public void setVideoTitleAmharic(String videoTitleAmharic) {
		this.videoTitleAmharic = videoTitleAmharic;
	}
	public String getVideoDescripAmharic() {
		return videoDescripAmharic;
	}
	public void setVideoDescripAmharic(String videoDescripAmharic) {
		this.videoDescripAmharic = videoDescripAmharic;
	}
        
        
	public String getVideoTitleEnglish() {
		return videoTitleEnglish;
	}
	public void setVideoTitleEnglish(String videoTitleEnglish) {
		this.videoTitleEnglish = videoTitleEnglish;
	}
	public String getVideoDescripEnglish() {
		return videoDescripEnglish;
	}
	public void setVideoDescripEnglish(String videoDescripEnglish) {
		this.videoDescripEnglish = videoDescripEnglish;
	}        
        
	public String getVideoUrlname() {
		return videoUrlname;
	}
	public void setVideoUrlname(String videoUrlname) {
		this.videoUrlname = videoUrlname;
	}
	public String getVideoLocation() {
		return videoLocation;
	}
	public void setVideoLocation(String videoLocation) {
		this.videoLocation = videoLocation;
	}
	public String getVideoThumbnail() {
		return videoThumbnail;
	}
	public void setVideoThumbnail(String videoThumbnail) {
		this.videoThumbnail = videoThumbnail;
	}
	public Long getSize() {
		return size;
	}
	public void setSize(Long size) {
		this.size = size;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}	
	public String getThumbnailInHours() {
		return thumbnailInHours;
	}
	public void setThumbnailInHours(String thumbnailInHours) {
		this.thumbnailInHours = thumbnailInHours;
	}
	
	public List<Videocategory> getVideocategory() {
		return videocategory;
	}
	public void setVideocategory(List<Videocategory> videocategory) {
		this.videocategory = videocategory;
	}

    /**
     * @return the videoSpecial
     */
    public Boolean getVideoSpecial() {
        return videoSpecial;
    }

    /**
     * @param videoSpecial the videoSpecial to set
     */
    public void setVideoSpecial(Boolean videoSpecial) {
        this.videoSpecial = videoSpecial;
    }
}
