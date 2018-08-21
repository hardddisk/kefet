package com.kefet.dao;


import com.kefet.model.Facebookdata;
import com.kefet.model.UsersFacebookdata;

public interface UsersFacebookdataDAO {
	
	
	void save(UsersFacebookdata usersFacebookdata);
	
	/**
	 * This method will return the userUname from users table 
	 * @param facebookUid
	 * @return
	 */
	String getUserUname(long facebookUid);
	
	/**
	 * This method will match if the user data exist in to the db then it will return users facebook info from facebookdata table.
	 * @param id the user id.
	 * @return user facebook info stored from facebookdata talbe.
	 */
	Facebookdata getFacebookdataByUserId(Integer id);
        
        /**
         * This method will return the association between users and facebookdata that exist in table USERS_FACEBOOKDATA table
         * @param id users Id
         * @return return the entire object of UsersFacebookdata which holds the association between users and facebookdata
         */
        UsersFacebookdata getUsersFacebookdataByUserId(Integer id);

}
