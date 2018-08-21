package com.kefet.dao;

import com.kefet.model.Authorizedauthor;

public interface AuthorizedauthorDAO {
	
     void save(Authorizedauthor authorizedauthor);
    
     void update(Authorizedauthor authorizedauthor);
    
     void delete(Authorizedauthor authorizedauthor);
    
     Authorizedauthor getAuthorizedauthor(java.lang.Short id);
    
     Authorizedauthor getAuthorizedauthorByEmail(String userEmail);

}
