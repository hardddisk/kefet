package com.kefet.dao.impl;

import java.io.Serializable;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kefet.dao.ArticleimageDAO;
import com.kefet.model.Articleimage;

@Repository
public class ArticleimageDAOImpl implements ArticleimageDAO , Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Logger log = Logger.getLogger(this.getClass());
	
        
    @Autowired
	private SessionFactory sessionFactory;

	/**
	 * Get Hibernate Session Factory
	 * 
	 * @return SessionFactory - Hibernate Session Factory
	 */
	
	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public void save(Articleimage articleimage) {
		getCurrentSession().save(articleimage);
	}

	@Override
	public void update(Articleimage articleimage) {
		log.info("------------------UPDATE-------------");
		Articleimage articleimageToUpdate = getArticleimage(articleimage.getId());
		
		articleimageToUpdate.setArticleArticleimages(articleimage.getArticleArticleimages());
		articleimageToUpdate.setArtImagekeyid(articleimage.getArtImagekeyid());
		articleimageToUpdate.setArtImgalt(articleimage.getArtImgalt());
		articleimageToUpdate.setArtImgcssclass(articleimage.getArtImgcssclass());
		articleimageToUpdate.setArtImgcssstyle(articleimage.getArtImgcssstyle());
		articleimageToUpdate.setArtImgsrc(articleimage.getArtImgsrc());
		articleimageToUpdate.setArtImgthumbHeight(articleimage.getArtImgthumbHeight());
		articleimageToUpdate.setArtImgthumbWidth(articleimage.getArtImgthumbWidth());
		getCurrentSession().update(articleimageToUpdate);
	}

	@Override
	public void delete(Articleimage articleimage) {
		log.info("------------------DELETE-------------");
		Articleimage articleimageToDelete=getArticleimage(articleimage.getId());
		if (articleimageToDelete != null)
			getCurrentSession().delete(articleimageToDelete);
	}

	@Override
	public Articleimage getArticleimage(Integer id) {
		return (Articleimage) getCurrentSession().get(Articleimage.class, id);
	}

	@Override
	public Articleimage selectByArtImagekeyid(String artImagekeyid) {
		Articleimage articleimage = (Articleimage) getCurrentSession().get(Articleimage.class, artImagekeyid);
		return articleimage;
	}

}
