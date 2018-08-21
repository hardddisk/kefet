package com.kefet.dao.impl;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.kefet.dao.UsersResumeDAO;
import com.kefet.model.UsersResume;

@Repository
public class UsersResumeDAOImpl implements UsersResumeDAO {
	
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
	public void save(UsersResume usersResume) {
		getCurrentSession().save(usersResume);
	}

	@Override
	public void update(UsersResume usersResume) {
		// TODO Auto-generated method stub
	}

}
