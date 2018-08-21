package com.kefet.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;
import com.kefet.dao.ArticleDAO;
import com.kefet.model.Article;
import com.kefet.model.Articlecategory;
import com.kefet.model.Articlelanguage;
import com.kefet.model.Articleshortdescription;
import com.kefet.model.Facebookdata;
import com.kefet.model.Imagealbum;
import com.kefet.model.Users;
import com.kefet.utility.article.Article_ArticleArticleimage;
import com.kefet.utility.model.ReadArticle;
import java.util.Iterator;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;


	
@Repository
public class ArticleDAOImpl implements ArticleDAO, Serializable  {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Logger log = Logger.getLogger(this.getClass());
	
        
    @Autowired
	private SessionFactory sessionFactory;
    
 
    private HibernateTemplate hibernateTemplate;

	/**
	 * Get Hibernate Session Factory
	 * 
	 * @return SessionFactory - Hibernate Session Factory
	 */
	private Session getCurrentSession() {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
		return sessionFactory.getCurrentSession();
	}
	

	@Override
	public Integer save(Article article) {
		getCurrentSession().save(article);
                return article.getId();
	}

	@Override
	public void update(Article article) {
		Article articleToUpdate = getArticle(article.getId());
		
		articleToUpdate.setArticlelanguage(article.getArticlelanguage());
		articleToUpdate.setUsers(article.getUsers());
		articleToUpdate.setArticlecategory(article.getArticlecategory());
		articleToUpdate.setArtTitle(article.getArtTitle());
		articleToUpdate.setArtContent(article.getArtContent());
		articleToUpdate.setArtWrittenDate(article.getArtWrittenDate());
		articleToUpdate.setArtPublishDate(article.getArtPublishDate());
		articleToUpdate.setArtCommentStatus(article.getArtCommentStatus());
		articleToUpdate.setArtCommentCount(article.getArtCommentCount());
		articleToUpdate.setArtViewcount(article.getArtViewcount());
		articleToUpdate.setArtLangIdntfy(article.getArtLangIdntfy());
		getCurrentSession().update(articleToUpdate);
	}
	
	@Override
	public void updateArtViewcount(Article article){
		log.info("------------------UPDATE-------------");
		getCurrentSession().update(article);
	}

	@Override
	public void delete(Article article) {
		log.info("------------------DELETE-------------");
		Article articleDelete=getArticle(article.getId());
		if (articleDelete != null)
			getCurrentSession().delete(articleDelete);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Article> selectAllArticles() {
		return getCurrentSession().createQuery("from Article").list();
	}

	@Override
	@SuppressWarnings("unchecked")
	 public List<Article> selectAllArticles(Integer startPage, Integer pageSize){
		DetachedCriteria criteria = DetachedCriteria.forClass(Article.class);
		criteria.addOrder(Order.desc("id"));
		return (List<Article>) hibernateTemplate.findByCriteria(criteria, startPage * pageSize, pageSize);
	}
         
        @Override
	@SuppressWarnings("unchecked")
         public List<Article> selectArticlesByUser(Users user, Integer startPage, Integer pageSize){
   
             DetachedCriteria criteria = DetachedCriteria.forClass(Article.class);
             criteria.add(Restrictions.eq("users",user));
	     criteria.addOrder(Order.desc("id"));  
             return (List<Article>) hibernateTemplate.findByCriteria(criteria, startPage * pageSize, pageSize);
         } 
         
        @Override
	public long rowCountByUserId(Integer userId){
		return (long) getCurrentSession().createQuery("select count(*) from Article where users.id = :id order by id desc").setInteger("id", userId).uniqueResult();
	}
	
	
	@Override
	public Article getArticle(java.lang.Integer id) {
		Article article = (Article) getCurrentSession().get(Article.class, id);
		return article;
	}
        
        @Override
	public ReadArticle getArticleForReading(java.lang.Integer id) {
           
            ReadArticle readArticle = null; 
            Iterator mylist=getCurrentSession().createQuery("select article, facebookdata, users from Article as article, Users as users, Facebookdata as facebookdata where article.id=:id and users.userUname = facebookdata.facebookUid and article.users.id = users.id").setParameter("id", id).list()
            .iterator();
            
            while (mylist.hasNext()) {
                readArticle = new ReadArticle();
                Object[] tuple = (Object[]) mylist.next();
                readArticle.setArticle((Article)tuple[0]);;
                readArticle.setFacebookdata((Facebookdata)tuple[1]);
                readArticle.setUsers((Users)tuple[2]);
            }
            
	   return readArticle;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Article> selectByArtLangIdntfy(String artLangIdntfy) {
	    List<Article> list = getCurrentSession().createQuery("from Article where artLangIdntfy = :artLangIdntfy")
	            .setParameter("artLangIdntfy", artLangIdntfy)
	            .list();
	        return list;
	}

	@Override
	public List<Article> selectByArtLangIdAndLangCode(String artLangId,
			String langCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Article> selectByArticlecategory(Articlecategory articlecategory) {
		List<Article> list = getCurrentSession().createQuery("from Article where articlecategory = :articlecategory")
	            .setParameter("articlecategory", articlecategory)
	            .list();
	        return list;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Article> selectByArticlecategory(Articlecategory articlecategory, int limitSize){
		List<Article> list = getCurrentSession().createQuery("from Article where articlecategory = :articlecategory order by id desc")
	            .setParameter("articlecategory", articlecategory).setMaxResults(limitSize)
	            .list();
	        return list;
	}

	@Override
	public List<Article> selectByArticlecategoryAndArticlelanguage(
			Articlecategory articlecategory, Articlelanguage articlelanguage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Article> selectByArticlecategoryPaginated(
			Articlecategory articlecategory, int pageOffset) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void hideArticle(Byte status, String id) {
		// TODO Auto-generated method stub

	}

	@Override
	@SuppressWarnings("unchecked")
	public Integer getMaxId() {
		Integer maxId;
        List<Integer> list = getCurrentSession().createQuery("select max(art.id) from Article art").list();
        if(list != null){
        	maxId = list.get(0);
        }else{
            maxId=0;
        }   
        return maxId;
	}
	
	public Long getRowCount(){
		return (Long) getCurrentSession().createCriteria(Article.class).setProjection(Projections.rowCount()).uniqueResult();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public Article selectJokeOfTheDay(){
		List<Article> list = getCurrentSession().createQuery("from Article  where articlecategory.catCode = :catCode order by id desc")
	            .setParameter("catCode", "jok")
	            .list();
	        return list.size() > 0 ?(Article)list.get(0): null;
	}
	

	
	@Override
	@SuppressWarnings("unchecked")
	public Article selectPoetryOfTheDay(){
		List<Article> list = getCurrentSession().createQuery("from Article  where articlecategory.catCode = :catCode order by id desc")
	            .setParameter("catCode", "ptr")
	            .list();
	        return list.size() > 0 ?(Article)list.get(0): null;
	} 
	
	@Override
	@SuppressWarnings("unchecked")
	public Article selectEssayOfTheDay(){
		List<Article> list = getCurrentSession().createQuery("from Article  where articlecategory.catCode = :catCode order by id desc")
	            .setParameter("catCode", "ess")
	            .list();
	        return list.size() > 0 ?(Article)list.get(0): null;
	} 
	
	@Override
	@SuppressWarnings("unchecked")
	public Article selectImageOfTheDay(){
		List<Article> list = getCurrentSession().createQuery("from Article  where articlecategory.catCode = :catCode order by id desc")
	            .setParameter("catCode", "imd")
	            .list();
	        return list.size() > 0 ?(Article)list.get(0): null;
	}
	
	
	 

	@Override
	public List<Articlelanguage> selectArticleLanguageByArtLangId(
			Integer artLangId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Article_ArticleArticleimage> getArticle_ArticleArticleimage(
			String catCode) {
		// TODO Auto-generated method stub
		return null;
	}

}
