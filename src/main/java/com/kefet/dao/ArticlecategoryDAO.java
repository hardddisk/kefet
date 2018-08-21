package com.kefet.dao;

import java.util.List;

import com.kefet.model.Articlecategory;

public interface ArticlecategoryDAO {
	
     void save(Articlecategory articlecategory);
    
     void update(Articlecategory articlecategory);
    
     void delete(Short id);
    
     Articlecategory getArticlecategory(Short id);
    
     Articlecategory selectByCatCode(String catCode);
    
    
     Articlecategory selectByCatName(String catName);
    
     List<Articlecategory> selectAllArticlecategory();

}
