package com.kefet.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kefet.dao.AuthorDAO;
import com.kefet.model.Author;


@Repository
public class AuthorDAOImpl implements AuthorDAO {
	
	/**
	 * 
	 */
	private final Logger log = Logger.getLogger(this.getClass());

    
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void save(Author author) {
		getCurrentSession().save(author);

	}

	@Override
	public void update(Author author) {
		getCurrentSession().update(author);
	}

	@Override
	public void delete(Author author) {
		
		Author authorToDelete = getAuthor(author.getId());
		if (authorToDelete != null)
			getCurrentSession().delete(authorToDelete);
	}
	
	@Override
	public Author getAuthor(java.lang.Short id) {
		Author author = (Author) getCurrentSession().get(Author.class, id);
		return author;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Author authorInfoExist(Integer id) {
		List<Author> list = getCurrentSession().createQuery("from Author where user.id = :id")
				.setParameter("id", id)
				.list();
		return list.size() > 0 ?(Author)list.get(0): null;
	}
}
