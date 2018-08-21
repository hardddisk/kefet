package com.kefet.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kefet.dao.AuthorArticleDAO;
import com.kefet.model.Article;
import com.kefet.model.Author;
import com.kefet.model.AuthorArticle;

@Repository
public class AuthorArticleDAOImpl implements AuthorArticleDAO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Logger log = Logger.getLogger(this.getClass());

    
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void save(AuthorArticle authorArticle) {
		getCurrentSession().save(authorArticle);
	}

	@Override
	public void update(AuthorArticle authorArticle) {
		// TODO Auto-generated method stub

	}
	
	@Override
	@SuppressWarnings("unchecked")
	public AuthorArticle getAuthorByArticle(Article article) {
		
		List<AuthorArticle> authorList = getCurrentSession().createQuery("from AuthorArticle where Article.id = :id")
	            .setParameter("id", article.getId())
	            .list();
		if(!authorList.isEmpty()){
			return authorList.get(0);
		}else{
			return null;
		}

	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<AuthorArticle> selectByAuthor(Author author) {
		 List<AuthorArticle> authorArticleList = getCurrentSession().createQuery("from AuthorArticle where Author.id = :id")
		            .setParameter("id", author.getId())
		            .list();
			return authorArticleList;
			
	}

}
