package com.kefet.dao;

import java.util.List;

import com.kefet.model.Article;
import com.kefet.model.Articlecategory;
import com.kefet.model.Articlelanguage;
import com.kefet.model.Users;
import com.kefet.utility.article.Article_ArticleArticleimage;
import com.kefet.utility.model.ReadArticle;

public interface ArticleDAO {
	
     Integer save(Article article);
    
     void update(Article article);
    
     void updateArtViewcount(Article article);
    
     long rowCountByUserId(Integer userId);
    
     void delete(Article article);
    
     List<Article> selectAllArticles();
    
     List<Article> selectAllArticles(Integer startPage, Integer pageSize);
    
     List<Article> selectArticlesByUser(Users user, Integer startPage, Integer pageSize);
     
     Article getArticle(java.lang.Integer id);
    
     ReadArticle getArticleForReading(java.lang.Integer id);
    
     List<Article> selectByArtLangIdntfy(String artLangIdntfy); 

     List<Article> selectByArtLangIdAndLangCode(String artLangId, String langCode);
    
     List<Article> selectByArticlecategory(Articlecategory articlecategory);
    
     List<Article> selectByArticlecategory(Articlecategory articlecategory, int limitSize);
    
     
     List<Article> selectByArticlecategoryAndArticlelanguage(Articlecategory articlecategory, Articlelanguage articlelanguage);
    
     List<Article> selectByArticlecategoryPaginated(Articlecategory articlecategory, int pageOffset);
    
     void hideArticle(Byte status, String id);
    
     Article selectJokeOfTheDay();
    
     Article selectPoetryOfTheDay();
    
     Article selectEssayOfTheDay();
    
     Article selectImageOfTheDay();
    
     Long getRowCount();
    
     Integer getMaxId();
    
     List<Articlelanguage> selectArticleLanguageByArtLangId(Integer artLangId);
           
     List<Article_ArticleArticleimage> getArticle_ArticleArticleimage(String catCode);

}
