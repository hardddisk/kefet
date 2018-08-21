package com.kefet.dao;

import java.util.List;

import com.kefet.model.ArticleArticleimage;
import com.kefet.model.ArticleArticleimageId;

public interface ArticleArticleimageDAO {
	
	
     void save(ArticleArticleimage articleArticleimage);
    
     void update(ArticleArticleimage articleArticleimage);
    
     void delete(ArticleArticleimage articleArticleimage);
    
     boolean delete(String artId);
    
     List<ArticleArticleimage> selectByArtImageId(Integer artImageId);
    
     List<ArticleArticleimage> selectByArticleId(Integer artId);
    
     ArticleArticleimage getArticleArticleimage(ArticleArticleimageId id);

	ArticleArticleimage getArticleArticleimage(Integer artId, Integer artImageId);

	ArticleArticleimage getArticleArticleimage(Integer artId);

}
