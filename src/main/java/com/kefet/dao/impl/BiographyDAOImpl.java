package com.kefet.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kefet.dao.BiographyDAO;
import com.kefet.model.Biography;
import com.kefet.model.UsersBiography;


@Repository
public class BiographyDAOImpl implements BiographyDAO , Serializable  {
	
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
	public void save(Biography biography) {
		getCurrentSession().save(biography);
	}
        
        @Override
        public void save(UsersBiography usersBiography) {
            getCurrentSession().save(usersBiography);
        }

	@Override
	public void update(Biography biography) {
		Biography biographyToUpdate = getBiographyById(biography.getId());
                log.debug("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                log.debug("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                log.debug("@@@@@@@@@@@@@@@@biographyToUpdate@@@@@@@@@@@@@@@@@@@@@@"+biographyToUpdate.getId());
                log.debug("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                log.debug("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		biographyToUpdate.setBiographycontent(biography.getBiographycontent());
		biographyToUpdate.setBiographyLang(biography.getBiographyLang());
		getCurrentSession().update(biographyToUpdate);
	}

	@Override
	public void delete(Biography biography) {
		Biography biographyDel=getBiographyById(biography.getId());
		if (biographyDel != null)
			getCurrentSession().delete(biographyDel);
	}

	@Override
	public Biography getBiographyById(Integer id) {
		Biography biography = (Biography) getCurrentSession().get(Biography.class, id);
		return biography;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Biography> getBiographyByUserId(Integer userId) {
		List<Biography> resumeList = getCurrentSession().
				createQuery("Select b from Biography b, Users u, UsersBiography ub "+
						    "where ub.users.id=u.id and "+
						    "ub.biography.id=b.id and "+
						    "ub.users.id=:userId").setInteger("userId", userId).list();       
	    return resumeList.size() > 0 ?(List<Biography>)resumeList: null;
	}

}
