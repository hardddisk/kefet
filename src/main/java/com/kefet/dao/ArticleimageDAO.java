package com.kefet.dao;

import com.kefet.model.Articleimage;

public interface ArticleimageDAO {
	
    
     void save(Articleimage articleimage);
    
     void update(Articleimage articleimage);
    
     void delete(Articleimage articleimage);
    
     Articleimage getArticleimage( Integer id);
    
     Articleimage selectByArtImagekeyid(String artImagekeyid);
    
 //   public List<Articleimage> selectByArticleArticleimageTeble

}
