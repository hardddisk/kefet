package com.kefet.dao;


import java.util.List;

import com.kefet.model.Users;

public interface UsersDAO{
	
	 void save(Users users);
	
	 void update(Users users);

	 void delete(Users users);
	
	 void updateUsersActivitylog(Integer id, String usersActivitylog);
        
     void updateUsersUsersPass(Integer id, String usersPass);

	 Users getUser(java.lang.Integer id); 
	
	 Users getUserByEmail(String email);
	
	 List<Users> findByExample(Users instance);

	 List<?> findByProperty(String propertyName, Object value);
	
	 List<Users> findByUsersFirst(String usersFirst);

	 List<Users> findByUsersLast(String usersLast);

	 List<Users> findByUsersPass(String usersPass);
	
	 List<Users> findByUsersGender(String usersGender);

	  Users getUserByUname(String usersUname);

	 List<Users> findByUsersEmailvalidurl(String usersEmailvalidurl);

	 List<Users> findByUsersValid(String usersValid);
	
	 List<Users> findByUserTerminate(String userTerminate);

	 List<Users> findByUsersActive(String usersActive);
	
	 List<?> findAll();

	 Users merge(Users detachedInstance);
	 
	 Integer getMaxId();

	 void attachDirty(Users instance);


}
