package com.kefet.dao.impl;

import java.io.Serializable;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;


import com.kefet.dao.ContestorsDAO;
import com.kefet.model.Contestors;


public class ContestorsDAOImpl implements ContestorsDAO , Serializable  {
	
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
	public void save(Contestors contestors) {
		getCurrentSession().save(contestors);
	}

	@Override
	public void update(Contestors contestors) {
		log.info("------------------UPDATE-------------");
		Contestors contestorsToUpdate = getContestor(contestors.getId());
		contestorsToUpdate.setContFirst(contestorsToUpdate.getContFirst());
		contestorsToUpdate.setContLast(contestorsToUpdate.getContLast());
		contestorsToUpdate.setContEmail(contestorsToUpdate.getContEmail());
		contestorsToUpdate.setContDescrip(contestorsToUpdate.getContDescrip());
		contestorsToUpdate.setContAdge(contestorsToUpdate.getContAdge());
		contestorsToUpdate.setContEducation(contestorsToUpdate.getContEducation());
		contestorsToUpdate.setContWork(contestorsToUpdate.getContWork());
		contestorsToUpdate.setContCity(contestorsToUpdate.getContCity());
		contestorsToUpdate.setContAspiration(contestorsToUpdate.getContAspiration());
		contestorsToUpdate.setContPhone(contestorsToUpdate.getContPhone());
		getCurrentSession().update(contestorsToUpdate);
	}

	@Override
	public void delete(Contestors contestors) {
		log.info("------------------DELETE-------------");
		getCurrentSession().delete(contestors);
	}

	@Override
	public Contestors getContestor(Integer id) {
		return (Contestors) getCurrentSession().get(Contestors.class, id);
	}

}
