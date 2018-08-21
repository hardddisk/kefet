package com.kefet.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kefet.dao.ImageDAO;
import com.kefet.model.Image;

@Repository
public class ImageDAOImpl implements ImageDAO , Serializable  {
	
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
	public void save(Image image) {
		getCurrentSession().save(image);
	}

	@Override
	public void update(Image image) {
		Image imageToUpdate = getImage(image.getId());
		imageToUpdate.setImagealbum(image.getImagealbum());
		imageToUpdate.setImageDescription(image.getImageDescription());
		imageToUpdate.setImgTittle(image.getImgTittle());
		imageToUpdate.setImageImagealbums(image.getImageImagealbums());
		imageToUpdate.setUsers(image.getUsers());
		getCurrentSession().update(imageToUpdate);

	}

	@Override
	public void delete(Image image) {
		Image imageDelete=getImage(image.getId());
		if (imageDelete != null)
			getCurrentSession().delete(imageDelete);
	}
	
	@Override
	public Image getImage(java.lang.Integer id) {
		return (Image) getCurrentSession().get(Image.class, id);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public Integer getMaxId(){		
		int retultRet=0;
		List<Integer> list=null;
		
		 list = getCurrentSession().createQuery("select Max(id) from Image").list();       
         if(list !=null){
             if(!list.isEmpty()){
                 if(list.get(0) != null){
                         retultRet = list.get(0);
                 }
             }                    
          }
         return retultRet;
	}
}
