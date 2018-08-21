package com.kefet.dao;

import java.util.List;

import com.kefet.model.Article;
import com.kefet.model.Articlecategory;
import com.kefet.model.Articleshortdescription;
import com.kefet.service.ArticleshortdescriptionService;



public interface ArticleshortdescriptionDAO {
	
     void save(Articleshortdescription articleshortdescription);
        
   
    
     void update(Articleshortdescription articleshortdescription);
    
     void delete(Articleshortdescription articleshortdescription);
    
     List<Articleshortdescription> getArticleshortdescriptionForCarousel();
    
     List<Articleshortdescription> getArticleshortdescriptionForLatestNews();
    
     List<Articleshortdescription> getWeeklyArticlesByArtViewcount(int limitSize);
    
     List<Articleshortdescription> getArticleshortdescriptionByArticleCategory(String catCode);
    
     List<Articleshortdescription> getArticleshortdescriptionByArticleCategory(String catCode, int limitSize);
    
     List<Articleshortdescription> getArticleshortdescriptionForLatestNews(Integer startPage, Integer pageSize);
    
     Articleshortdescription getArticleshortdescriptionByArticleId(Article article);
    
     Articleshortdescription getArticleshortdescription(java.lang.Integer id);
    
     List<Articleshortdescription> selectByArtLangIdntfy(String artLangIdntfy); 
    
     List<Articleshortdescription> selectByArticlecategory(Articlecategory articlecategory, Integer startPage, Integer pageSize); 
    
    List<Articleshortdescription> getArticleshortdescriptionServiceByReadCount(Integer startPage, Integer pageSize);
    
     long rowCountByArticlecategory(String catCode);
    
     List<Articleshortdescription> selectAllArticleshortdescription();
    
     Long getRowCount();
    

}

