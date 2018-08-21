package com.kefet.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kefet.dao.ArticlecategoryDAO;
import com.kefet.model.Articlecategory;

@Repository
public class ArticlecategoryDAOImpl implements ArticlecategoryDAO, Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private final Logger log = Logger.getLogger(this.getClass());

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * Get Hibernate Session Factory
     *
     * @return SessionFactory - Hibernate Session Factory
     */
    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void save(Articlecategory articlecategory) {
        getCurrentSession().save(articlecategory);
    }

    @Override
    public void update(Articlecategory articlecategory) {
        log.info("------------------UPDATE-------------");
        Articlecategory articlecategoryToUpdate = getArticlecategory(articlecategory.getId());
        articlecategoryToUpdate.setCatName(articlecategory.getCatName());
        articlecategoryToUpdate.setCatCode(articlecategory.getCatCode());
        articlecategoryToUpdate.setCatTotArt(articlecategory.getCatTotArt());
        getCurrentSession().update(articlecategoryToUpdate);
    }

    @Override
    public void delete(Short id) {
        log.info("------------------DELETE-------------");
        Articlecategory articlecategory = getArticlecategory(id);
        if (articlecategory != null) {
            getCurrentSession().delete(articlecategory);
        }
    }

    @Override
    public Articlecategory getArticlecategory(Short id) {
        Articlecategory articlecategory = (Articlecategory) getCurrentSession().get(Articlecategory.class, id);
        return articlecategory;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Articlecategory selectByCatCode(String catCode) {
        List<Articlecategory> list = getCurrentSession().createQuery("from Articlecategory where catCode = :catCode")
                .setParameter("catCode", catCode)
                .list();
        return list.size() > 0 ? (Articlecategory) list.get(0) : null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Articlecategory selectByCatName(String catName) {
        List<Articlecategory> list = getCurrentSession().createQuery("from Articlecategory where catName = :catName")
                .setParameter("catName", catName)
                .list();
        return list.size() > 0 ? (Articlecategory) list.get(0) : null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Articlecategory> selectAllArticlecategory() {
        return getCurrentSession().createQuery("from Articlecategory").list();
    }

}
