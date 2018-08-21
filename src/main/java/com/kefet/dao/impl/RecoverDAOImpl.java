package com.kefet.dao.impl;

import java.io.Serializable;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kefet.dao.RecoverDAO;
import com.kefet.model.Recover;
import com.kefet.model.Users;
import java.util.List;

@Repository
public class RecoverDAOImpl implements RecoverDAO, Serializable {

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
    public void save(Recover recover) {
        getCurrentSession().save(recover);
    }

    @Override
    public void update(Recover recover) {
        Recover recoverToUpdate = getByUsersId(recover.getUsersId());
        recoverToUpdate.setUsers(recover.getUsers());
        recoverToUpdate.setUsersPasskey(recover.getUsersPasskey());
        getCurrentSession().update(recoverToUpdate);
    }

    @Override
    public void delete(Recover recover) {
        getCurrentSession().delete(recover);
    }

    @Override
    public Recover getByUsersId(Integer usersId) {
        return (Recover) getCurrentSession().get(Recover.class, usersId);
    }

}
