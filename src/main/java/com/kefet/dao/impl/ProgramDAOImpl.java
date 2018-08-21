/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kefet.dao.impl;

import com.kefet.dao.ProgramDAO;
import com.kefet.model.Program;
import java.io.Serializable;
import java.util.List;
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
public class ProgramDAOImpl implements ProgramDAO, Serializable  {
	
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private final Logger log = Logger.getLogger(this.getClass());


    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
    
    

    @Override
    public void save(Program program) {
        getCurrentSession().save(program);
    }
   
    @Override
    public void update(Program program) {
        Program programToUpdate = getProgram(program.getId());
        programToUpdate.setPrgFbTitle(program.getPrgFbTitle());
        programToUpdate.setPrgBndleName(program.getPrgBndleName());
        programToUpdate.setPrgRelease(program.getPrgRelease());
        programToUpdate.setPrgLate(program.getPrgLate());
        programToUpdate.setPrgAdjust(program.getPrgAdjust());
        programToUpdate.setPrgAdjstDesc(program.getPrgAdjstDesc());	
	getCurrentSession().update(programToUpdate);
    }

    @Override
    public void delete(Program program) {
        Program programDelete=getProgram(program.getId());
        if (programDelete != null){
            getCurrentSession().delete(programDelete);
        }
    }

    @Override
    public Program getProgram(Integer id) {
        return (Program) getCurrentSession().get(Program.class, id);
    }
    
    @Override
    public Long getNumberOfProgram(){
      //  return (Long) getCurrentSession().createCriteria(Program).setProjection(Projections.rowCount()).uniqueResult();
       
        return ((Long) getCurrentSession().createQuery("select count(*) from Program").uniqueResult());
    }
    
     
    @Override
    @SuppressWarnings("unchecked")
    public List<Program> getPrograms( Integer startPage, Integer pageSize) {
        return  getCurrentSession().createQuery("from Program")
                .setFirstResult(startPage * pageSize).setMaxResults(pageSize).list();

            //https://forum.hibernate.org/viewtopic.php?f=1&t=1004293
    }
    
}
