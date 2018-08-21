package com.kefet.dao;

import java.util.List;

import com.kefet.model.Article;
import com.kefet.model.Author;
import com.kefet.model.AuthorArticle;

public interface AuthorArticleDAO {
	
     void save(AuthorArticle authorArticle);
    
     void update(AuthorArticle authorArticle);
    
     AuthorArticle getAuthorByArticle(Article article);
    
     List<AuthorArticle> selectByAuthor(Author author);
}
