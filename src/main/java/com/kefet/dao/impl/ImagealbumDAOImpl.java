package com.kefet.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.kefet.dao.ImagealbumDAO;
import com.kefet.model.Imagealbum;


@Repository
public class ImagealbumDAOImpl implements ImagealbumDAO , Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Logger log = Logger.getLogger(this.getClass());

	
	@Autowired
	private SessionFactory sessionFactory;
    
	 private HibernateTemplate hibernateTemplate;

		/**
		 * Get Hibernate Session Factory
		 * 
		 * @return SessionFactory - Hibernate Session Factory
		 */
		private Session getCurrentSession() {
			this.hibernateTemplate = new HibernateTemplate(sessionFactory);
			return sessionFactory.getCurrentSession();
		}
		
	@Override
	public void save(Imagealbum imagealbum) {
		getCurrentSession().save(imagealbum);
	}

	
	@Override
	public void update(Imagealbum imagealbum) {
		log.info("------------------UPDATE-------------");
		Imagealbum imagealbumToUpdate = getImagealbum(imagealbum.getId());
		imagealbumToUpdate.setAlbumName(imagealbumToUpdate.getAlbumName());
		imagealbumToUpdate.setImages(imagealbumToUpdate.getImages());
		imagealbumToUpdate.setAlbumSelected(imagealbum.getAlbumSelected());
		getCurrentSession().update(imagealbumToUpdate);
	}
	
	

	@Override
	public void delete(Imagealbum imagealbum) {
		log.info("------------------DELETE-------------");
		getCurrentSession().delete(imagealbum);
	}

	@Override
	public Imagealbum getImagealbum(Integer id) {
		return (Imagealbum) getCurrentSession().get(Imagealbum.class, id);
		
	}
	
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Imagealbum> getAllImagealbum(Integer startPage, Integer pageSize) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Imagealbum.class);
		criteria.addOrder(Order.desc("id"));
		return (List<Imagealbum>) hibernateTemplate.findByCriteria(criteria, startPage * pageSize, pageSize);
	}

	@Override
	@SuppressWarnings("unchecked")
	public Imagealbum getImagealbumByAlbumName(String albumName) {
		List<Imagealbum> list = getCurrentSession().createQuery("from Imagealbum where albumName=:albumName").setString("albumName", albumName).list();       
		 return list.size() > 0 ?(Imagealbum)list.get(0): null;
	}
	
	@Override
	public Long getRowCount(){
		return (Long) getCurrentSession().createCriteria(Imagealbum.class).setProjection(Projections.rowCount()).uniqueResult();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Imagealbum> getSelectedImagealbumByAlbumSelected(){
		List<Imagealbum> imagealbumList = getCurrentSession().createQuery("from Imagealbum where albumSelected=:albumSelected").setBoolean("albumSelected", true).list();
		return imagealbumList;
	}

}
