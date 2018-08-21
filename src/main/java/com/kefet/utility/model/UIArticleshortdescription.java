package com.kefet.utility.model;

import org.hibernate.validator.constraints.NotEmpty;

public class UIArticleshortdescription {
	
	
	@NotEmpty
	private String id;
	@NotEmpty
	private String imageCode;
	@NotEmpty
	private String artTitle;
	@NotEmpty
	private String artShortDescrp;
	
	@NotEmpty
	private String artCarousel;
	
	@NotEmpty
	private String descriptionImageTemplate;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getImageCode() {
		return imageCode;
	}
	public void setImageCode(String imageCode) {
		this.imageCode = imageCode;
	}
	public String getArtTitle() {
		return artTitle;
	}
	public void setArtTitle(String artTitle) {
		this.artTitle = artTitle;
	}
	public String getArtShortDescrp() {
		return artShortDescrp;
	}
	public void setArtShortDescrp(String artShortDescrp) {
		this.artShortDescrp = artShortDescrp;
	}
	
	public String getArtCarousel() {
		return artCarousel;
	}
	public void setArtCarousel(String artCarousel) {
		this.artCarousel = artCarousel;
	}
	
	public String getDescriptionImageTemplate() {
		return descriptionImageTemplate;
	}
	public void setDescriptionImageTemplate(String descriptionImageTemplate) {
		this.descriptionImageTemplate = descriptionImageTemplate;
	}

}
