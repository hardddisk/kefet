package com.kefet.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kefet.dao.AuthorizedauthorDAO;
import com.kefet.model.Articlecategory;
import com.kefet.model.Authorizedauthor;

@Repository
public class AuthorizedauthorDAOImpl implements AuthorizedauthorDAO {
	
	/**
	 * 
	 */
	
	private final Logger log = Logger.getLogger(this.getClass());

    
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void save(Authorizedauthor authorizedauthor) {
		getCurrentSession().save(authorizedauthor);
	}

	@Override
	public void update(Authorizedauthor authorizedauthor) {
		getCurrentSession().update(authorizedauthor);

	}

	@Override
	public void delete(Authorizedauthor authorizedauthor) {
		
		Authorizedauthor authorizedauthorToDelete = getAuthorizedauthor(authorizedauthor.getId());
		if (authorizedauthorToDelete != null)
			getCurrentSession().delete(authorizedauthorToDelete);

	}
	
	@Override
	public Authorizedauthor getAuthorizedauthor(java.lang.Short id) {
		Authorizedauthor authorizedauthor = (Authorizedauthor) getCurrentSession().get(Authorizedauthor.class, id);
		return authorizedauthor;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public Authorizedauthor getAuthorizedauthorByEmail(String userEmail){
		List<Authorizedauthor> list = getCurrentSession().createQuery("from Authorizedauthor where userEmail = :userEmail")
	            .setParameter("userEmail", userEmail)
	            .list();
	        return list.size() > 0 ?(Authorizedauthor)list.get(0): null;
	}
}
