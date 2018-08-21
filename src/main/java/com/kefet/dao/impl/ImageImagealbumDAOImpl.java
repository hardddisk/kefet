package com.kefet.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kefet.dao.ImageImagealbumDAO;
import com.kefet.model.ImageImagealbum;
import com.kefet.model.Imagealbum;

@Repository
public class ImageImagealbumDAOImpl implements ImageImagealbumDAO , Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Logger log = Logger.getLogger(this.getClass());

    
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void save(ImageImagealbum imageImagealbum) {
		getCurrentSession().save(imageImagealbum);
	}

	/*
	
	@Override
	public void update(ImageImagealbum imageImagealbum) {
		ImageImagealbum imageImagealbumToUpdate = getImageImagealbum(imageImagealbum.getId());
	}
	
	*/

	@Override
	public void delete(ImageImagealbum imageImagealbum) {
		getCurrentSession().delete(imageImagealbum);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<ImageImagealbum> selectByImagealbum(Imagealbum imagealbum) {
		 List<ImageImagealbum> imageImagealbumList = getCurrentSession().createQuery("from ImageImagealbum where Imagealbum.id = :id")
		            .setParameter("id", imagealbum.getId())
		            .list();
			return imageImagealbumList;
			
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<ImageImagealbum> selectByImagealbumSelectedAlbum(){
		 List<ImageImagealbum> imageImagealbumList = getCurrentSession()
				 .createQuery("from ImageImagealbum where imagealbum.albumSelected= :albumSelected")
				 .setBoolean("albumSelected", true)
		         .list();
			return imageImagealbumList;
	}

	
}
