package com.kefet.dao;

import java.util.List;

import com.kefet.model.Articlelanguage;



public interface ArticlelanguageDAO {
	
	
	 void save(Articlelanguage articlelanguage);
	
	 void update(Articlelanguage articlelanguage);

	 void delete(Articlelanguage articlelanguage);
	
	 List<Articlelanguage> selectAllArticlelanguage();
	
	 Articlelanguage getArticlelanguage(java.lang.Short id); 
	
	  Articlelanguage getByLangCode(String langCode);
	
	  Articlelanguage getByLanguageName(String languageName);

}
