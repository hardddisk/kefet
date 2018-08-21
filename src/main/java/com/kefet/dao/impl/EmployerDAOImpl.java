/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kefet.dao.impl;

import com.kefet.dao.EmployerDAO;
import com.kefet.model.Employer;
import java.io.Serializable;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author hardddisk
 */
@Repository
public class EmployerDAOImpl implements EmployerDAO , Serializable  {
	
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
    public void save(Employer employer) {
        getCurrentSession().save(employer);
    }

    @Override
    public void update(Employer employer) {
        Employer employerToUpdate = getEmployer(employer.getId());
        employerToUpdate.setEmpCompanyName(employer.getEmpCompanyName());
        employerToUpdate.setEmpContanctName(employer.getEmpContanctName());
        employerToUpdate.setEmpEmail(employer.getEmpEmail());
        employerToUpdate.setEmpPass(employer.getEmpPass());
        employerToUpdate.setEmpPrimaryPhone(employer.getEmpPrimaryPhone());
        employerToUpdate.setEmpVercode(employer.getEmpVercode());
        employerToUpdate.setEmpActive(employer.getEmpActive());
        getCurrentSession().merge(employer);
    }

    @Override
    public void delete(Employer employer) {
        Employer employerDelete=getEmployer(employer.getId());
        if (employerDelete != null){
            getCurrentSession().delete(employerDelete);
        }
    }

    @Override
    public Employer getEmployer(Integer id) {
        return (Employer) getCurrentSession().get(Employer.class, id);
    }   
}