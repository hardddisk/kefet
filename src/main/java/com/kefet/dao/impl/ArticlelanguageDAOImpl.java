package com.kefet.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kefet.dao.ArticlelanguageDAO;
import com.kefet.model.Articlelanguage;



@Repository
public class ArticlelanguageDAOImpl implements ArticlelanguageDAO, Serializable  {

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
	public Session getSessionFactory() {
		return sessionFactory.getCurrentSession();
	}

	
	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
	
	
	@Override
	public void save(Articlelanguage articlelanguage) {
		getCurrentSession().save(articlelanguage);
	}

	@Override
	public void update(Articlelanguage articlelanguage) {
		log.info("------------------UPDATE-------------");
		Articlelanguage articlelanguageToUpdate = getArticlelanguage(articlelanguage.getId());
		articlelanguageToUpdate.setLangCode(articlelanguage.getLangCode());
		articlelanguageToUpdate.setLanguageName(articlelanguage.getLanguageName());
		getCurrentSession().update(articlelanguageToUpdate);

	}

	@Override
	public void delete(Articlelanguage articlelanguage) {
		log.info("------------------DELETE-------------");
		Articlelanguage articlelanguageDelete=getArticlelanguage(articlelanguage.getId());
		if (articlelanguageDelete != null)
			getCurrentSession().delete(articlelanguageDelete);
	}
	
	@Override
	public Articlelanguage getArticlelanguage(Short id) {
		Articlelanguage articlelanguage = (Articlelanguage) getCurrentSession().get(Articlelanguage.class, id);
		return articlelanguage;
	}


	@Override
	@SuppressWarnings("unchecked")
	public List<Articlelanguage> selectAllArticlelanguage() {
		return getCurrentSession().createQuery("from Articlelanguage").list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public Articlelanguage getByLangCode(String langCode) {
	    List<Articlelanguage> list = getCurrentSession().createQuery("from Articlelanguage where langCode = :langCode")
	            .setParameter("langCode", langCode)
	            .list();
	        return list.size() > 0 ?(Articlelanguage)list.get(0): null;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Articlelanguage getByLanguageName(String languageName) {
	    List<Articlelanguage> list = getCurrentSession().createQuery("from Articlelanguage where languageName = :languageName")
	            .setParameter("languageName", languageName)
	            .list();
	        return list.size() > 0 ?(Articlelanguage)list.get(0): null;
	}

}
