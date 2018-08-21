package com.kefet.utility.model;

import java.util.Date;
import org.hibernate.validator.constraints.NotEmpty;

public class UIArticle implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	@NotEmpty
	private String articleLanguage;	
	@NotEmpty
	private String articleCategory;
	@NotEmpty
	private String artTitle;
	@NotEmpty
	private String artContent;
	@NotEmpty
	private String artpublished;
        	
	private String artImgsrc;
        
        private Date artPublishDate;
        
        private Integer artViewcount;
	
	private boolean isArtLinkVisible;
        
        private Boolean artSpecial;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getArticleLanguage() {
		return articleLanguage;
	}

	public void setArticleLanguage(String articleLanguage) {
		this.articleLanguage = articleLanguage;
	}

	public String getArticleCategory() {
		return articleCategory;
	}

	public void setArticleCategory(String articleCategory) {
		this.articleCategory = articleCategory;
	}

	public String getArtTitle() {
		return artTitle;
	}

	public void setArtTitle(String artTitle) {
		this.artTitle = artTitle;
	}

	public String getArtContent() {
		return artContent;
	}

	public void setArtContent(String artContent) {
		this.artContent = artContent;
	}

	public String getArtpublished() {
		return artpublished;
	}

	public void setArtpublished(String artpublished) {
		this.artpublished = artpublished;
	}
	
	public String getArtImgsrc() {
		return artImgsrc;
	}

	public void setArtImgsrc(String artImgsrc) {
		this.artImgsrc = artImgsrc;
	}

	public boolean isArtLinkVisible() {
		return isArtLinkVisible;
	}

	public void setArtLinkVisible(boolean isArtLinkVisible) {
		this.isArtLinkVisible = isArtLinkVisible;
	}

    /**
     * @return the artPublishDate
     */
    public Date getArtPublishDate() {
        return artPublishDate;
    }

    /**
     * @param artPublishDate the artPublishDate to set
     */
    public void setArtPublishDate(Date artPublishDate) {
        this.artPublishDate = artPublishDate;
    }

    /**
     * @return the artViewcount
     */
    public Integer getArtViewcount() {
        return artViewcount;
    }

    /**
     * @param artViewcount the artViewcount to set
     */
    public void setArtViewcount(Integer artViewcount) {
        this.artViewcount = artViewcount;
    }

    /**
     * @return the artSpecial
     */
    public Boolean getArtSpecial() {
        return artSpecial;
    }

    /**
     * @param artSpecial the artSpecial to set
     */
    public void setArtSpecial(Boolean artSpecial) {
        this.artSpecial = artSpecial;
    }
}
