package com.kefet.dao.impl;

import java.io.Serializable;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.kefet.dao.FacebookdataDAO;
import com.kefet.model.Facebookdata;

@Repository
public class FacebookdataDAOImpl implements FacebookdataDAO , Serializable  {
	
/**
 * 
 */
private static final long serialVersionUID = 1L;
    
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
	public void save(Facebookdata facebookdata) {
		getCurrentSession().save(facebookdata);
	}


	@Override
	public void update(Facebookdata facebookdata) {
		Facebookdata facebookToUpdate=getFacebookdata(facebookdata.getFacebookUid());
		facebookToUpdate.setFacebookCoverLocation(facebookdata.getFacebookCoverLocation());
		facebookToUpdate.setFacebookEditedCoverLocation(facebookdata.getFacebookEditedCoverLocation());
		facebookToUpdate.setFacebookProfileLocationLrg(facebookdata.getFacebookProfileLocationLrg());
		facebookToUpdate.setFacebookProfileLocationSml(facebookdata.getFacebookProfileLocationSml());
		facebookToUpdate.setUsersFacebookdatas(facebookdata.getUsersFacebookdatas());
		getCurrentSession().update(facebookToUpdate);
		
	}


	@Override
	public Facebookdata getFacebookdata(long facebookUid) {
		Facebookdata facebookdata = (Facebookdata) getCurrentSession().get(Facebookdata.class, facebookUid);
		return facebookdata;
	}
	
}
