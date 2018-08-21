package com.kefet.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kefet.dao.UsersJobsDAO;
import com.kefet.model.Users;
import org.hibernate.Query;

@Repository
public class UsersJobsDAOImpl implements UsersJobsDAO{
	
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
	@SuppressWarnings("unchecked")
	public List<String> getUsersSelectedJob(Users user) {
		
		List<String> jobList = getCurrentSession().
				createQuery("Select j.jobValue from Jobs j, UsersJobs uj where "
						+ "j.id=uj.jobs.id and "
						+ "uj.users.id=:userId").setString("userId", user.getId().toString()).list();       
	    return jobList.size() > 0 ?(List<String>)jobList : null;
	}

    @Override
    public int deleteUsersJobs(Users user) {
        Query query;
            query = getCurrentSession().createQuery("delete UsersJobs where users.id=:userId");
            query.setParameter("userId", user.getId());
       
       return query.executeUpdate();

        
    }
	
}
