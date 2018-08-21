package com.kefet.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kefet.dao.UsersFacebookdataDAO;
import com.kefet.model.Facebookdata;
import com.kefet.model.UsersFacebookdata;

@Repository
public class UsersFacebookdataDAOImpl implements UsersFacebookdataDAO {
	
        
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
	public void save(UsersFacebookdata usersFacebookdata) {
		getCurrentSession().save(usersFacebookdata);
	}

	@Override
	@SuppressWarnings("unchecked")
	public String getUserUname(long facebookUid) {
		List<String> userName=getCurrentSession().createSQLQuery("select u.USER_UNAME from "
				+ "USERS u, USERS_FACEBOOKDATA uf, FACEBOOKDATA f "
				+ "WHERE f.FACEBOOK_UID = uf.FACEBOOKDATA_UID and "
				+ "uf.USERS_ID = u.ID and "
				+ "uf.FACEBOOKDATA_UID =:facebookUid").setLong("facebookUid", facebookUid).list();
		
		return userName.size() > 0 ?(String)userName.get(0): null;
		
	}

	@Override
	@SuppressWarnings("unchecked")
	public Facebookdata getFacebookdataByUserId(Integer id) {
		List<Facebookdata> facebookdata=(List<Facebookdata>) getCurrentSession().createQuery("select f from Facebookdata f, Users u, UsersFacebookdata uf where u.id=uf.users.id and uf.facebookdata.facebookUid=f.facebookUid and u.id=:id").setInteger("id", id).list();
		return facebookdata.size() > 0 ?(Facebookdata)facebookdata.get(0): null;
	}

    @Override
    public UsersFacebookdata getUsersFacebookdataByUserId(Integer id) {
        List<UsersFacebookdata> usersFacebookdataList=(List<UsersFacebookdata>)
                getCurrentSession().createQuery("Select uf from UsersFacebookdata uf "
                        + "where uf.users.id =:id").setInteger("id", id).list();
        return usersFacebookdataList.size() > 0 ? (UsersFacebookdata)usersFacebookdataList.get(0): null;
    }

}
