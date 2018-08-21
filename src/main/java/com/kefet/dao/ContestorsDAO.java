package com.kefet.dao;

import com.kefet.model.Contestors;

public interface ContestorsDAO {
	
	
	 void save(Contestors contestors);
    
     void update(Contestors contestors);
    
     void delete(Contestors contestors);
    
     Contestors getContestor(java.lang.Integer id);
    
    

}
