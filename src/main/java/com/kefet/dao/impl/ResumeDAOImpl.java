package com.kefet.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kefet.dao.ResumeDAO;
import com.kefet.model.Resume;
import com.kefet.model.Users;


@Repository
public class ResumeDAOImpl implements ResumeDAO , Serializable  {
	
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
	public void save(Resume resume) {
		getCurrentSession().save(resume);
	}

	@Override
	public void update(Resume resume) {
		Resume resumeToUpdate = getResume(resume.getId());
		resumeToUpdate.setResumeBody(resume.getResumeBody());
		resumeToUpdate.setResumeLang(resume.getResumeLang());
		getCurrentSession().update(resumeToUpdate);
	}

	@Override
	public void delete(Resume resume) {
		Resume resumeDel=getResume(resume.getId());
		if (resumeDel != null)
			getCurrentSession().delete(resumeDel);
	}

	@Override
	public Resume getResume(Integer id) {
		Resume resume = (Resume) getCurrentSession().get(Resume.class, id);
		return resume;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Resume> getResumeByUser(Users user) {
		List<Resume> resumeList = getCurrentSession().
				createQuery("Select r from Resume r, Users u, UsersResume ur "+
						    "where ur.users.id=u.id and "+
						    "ur.resume.id=r.id and "+
						    "ur.users.id=:userId").setString("userId", user.getId().toString()).list();       
	    return resumeList.size() > 0 ?(List<Resume>)resumeList: null;
	}
}
