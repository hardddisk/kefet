package com.kefet.dao;

import java.util.List;

import com.kefet.model.Biography;
import com.kefet.model.UsersBiography;


public interface BiographyDAO {
	
	void save(Biography biography);
        
        void save(UsersBiography usersBiography);
	
	void update(Biography biography);
	
	void delete(Biography biography);
	
	Biography getBiographyById(Integer id);
	
	List<Biography> getBiographyByUserId(Integer userId);

}
