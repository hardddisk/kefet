package com.kefet.dao;

import com.kefet.model.Author;


public interface AuthorDAO {
	
     void save(Author author);
    
     void update(Author author);
    
     void delete(Author author);
    
     Author getAuthor(java.lang.Short id);
    
    /**
     * This method will tell us if the user is authorized author, and if he already filled his information and resume to the system.
     * @param users user info from Users table 
     * @return Author object if he has all the filled on this table filled 
     */
     Author authorInfoExist(Integer id);

}
