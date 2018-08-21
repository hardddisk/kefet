package com.kefet.dao.impl;

import com.kefet.dao.UsersDAO;
import com.kefet.model.Users;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class UsersDAOImpl implements UsersDAO, Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Logger LOGGER = Logger.getLogger(this.getClass());
	
        
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
	public void save(Users users) {
		getCurrentSession().save(users);
	}

	@Override
	public void update(Users user) {
		Users userToUpdate = getUser(user.getId());
		userToUpdate.setUserFirst(user.getUserFirst());
		userToUpdate.setUserLast(user.getUserLast());
		userToUpdate.setUserEmail(user.getUserEmail());
		userToUpdate.setUserPass(user.getUserPass());
		userToUpdate.setUserAdge(user.getUserAdge());
		userToUpdate.setUserActivitylog(user.getUserActivitylog());
		userToUpdate.setUserJoined(user.getUserJoined());
		userToUpdate.setUserGender(user.getUserGender());
		userToUpdate.setUserUname(user.getUserUname());
		userToUpdate.setUserValid(user.getUserValid());
		userToUpdate.setUserTerminate(user.getUserTerminate());
		userToUpdate.setUserActive(user.getUserActive());
		userToUpdate.setUsersJobses(user.getUsersJobses());
		getCurrentSession().update(userToUpdate);
	}
	
	@Override
	public Users getUser(Integer id){
		Users user = (Users) getCurrentSession().get(Users.class, id);
		return user;
	}

	@Override
	public void delete(Users users)  {
		Users user=getUser(users.getId());
		if (user != null)
			getCurrentSession().delete(user);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public Users getUserByEmail(String email) {
	    List<Users> list = getCurrentSession().createQuery("from Users where userEmail=:usersEmail").setString("usersEmail", email).list();       
	    return list.size() > 0 ?(Users)list.get(0): null;
	}

	@Override
	public void updateUsersActivitylog(Integer id, String usersActivitylog) {
		
		getCurrentSession().beginTransaction();
		 try{
			Query query =getCurrentSession().createQuery("update Users set usersActivitylog = :usersActivitylog" +
    				" where id = :id").setString("usersActivitylog", usersActivitylog).setString("id", id.toString());
                         query.executeUpdate();
                         
                         getCurrentSession().getTransaction().commit();
		 }catch(Exception e){
			 getCurrentSession().getTransaction().rollback();
			 LOGGER.error("UsersDAOImpl -- not able to delete");
		 }finally{
			 getCurrentSession().close();
		 }
	}      

    @Override
    public void updateUsersUsersPass(Integer id, String usersPass) {
  		
    	getCurrentSession().beginTransaction();
		 try{
			 Query query = getCurrentSession().createQuery("update Users set usersPass = :usersPass" +
    				" where id = :id").setString("usersPass", usersPass).setString("id", id.toString());
                         query.executeUpdate();
                         getCurrentSession().getTransaction().commit();
		 }catch(Exception e){
			 getCurrentSession().getTransaction().rollback();
			 LOGGER.error("UsersDAOImpl -- not able to updateUsersUsersPass");
		 }finally{
			 getCurrentSession().close();
		 }
    }


	@Override
	public List<Users> findByExample(Users instance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<?> findByProperty(String propertyName, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Users> findByUsersFirst(String usersFirst) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Users> findByUsersLast(String usersLast) {
		// TODO Auto-generated method stub
		return null;
	}
	

	
	@Override
	@SuppressWarnings("unchecked")
	public Users getUserByUname(String userUname) {
	    List<Users> list = getCurrentSession().createQuery("from Users where userUname = :userUname")
	            .setParameter("userUname", userUname)
	            .list();
	        return list.size() > 0 ?(Users)list.get(0): null;
	}
	
	@Override
	public List<Users> findByUsersPass(String usersPass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Users> findByUsersGender(String usersGender) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Users> findByUsersEmailvalidurl(String usersEmailvalidurl) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Users> findByUsersValid(String usersValid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Users> findByUserTerminate(String userTerminate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Users> findByUsersActive(String usersActive) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<?> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Users merge(Users detachedInstance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void attachDirty(Users instance) {
		// TODO Auto-generated method stub

	}

    
    	/**
	   * Get a Class object representing the Entity class for a given table name.
	   * @param tablename
	   * @return Class
	   */
	  public static Class<?> getEntityClassFor(String tablename) {
		  Class<?> entityClass = null;
		  try {
			  entityClass = Class.forName("com.kefet.model." + tablename);
		  }
		  catch (Exception e) {
			  
			  // TODO must have log statement
			  System.out.println("getEntityClassFor():" + e.toString());
			  
			  //LOGGER.error("getEntityClassFor():" + e.toString());
		  }
		  return entityClass;
	  }
	  
	  @Override
	  public Integer getMaxId(){
		  Integer maxId;
	        @SuppressWarnings("unchecked")
			List<Integer> list = getCurrentSession().createQuery("select max(id) from Users").list();
	        if(list != null){
	        	maxId = list.get(0);
	        }else{
	            maxId=0;
	        }   
	        return maxId;
		  
	  }

}


