package com.kefet.dao.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.kefet.dao.ArticleshortdescriptionDAO;
import com.kefet.model.Article;
import com.kefet.model.Articlecategory;
import com.kefet.model.Articleshortdescription;
import com.kefet.utility.Utility;

@Repository
public class ArticleshortdescriptionDAOImpl implements
		ArticleshortdescriptionDAO, Serializable  {
	
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
	public void save(Articleshortdescription articleshortdescription) {
		getCurrentSession().save(articleshortdescription);
	}

	@Override
	public void update(Articleshortdescription articleshortdescription) {
		log.info("------------------UPDATE-------------");
		Articleshortdescription articleshortdescriptionToUpdate = getArticleshortdescription(articleshortdescription.getId());            
		articleshortdescriptionToUpdate.setArticleimage(articleshortdescription.getArticleimage());
		articleshortdescriptionToUpdate.setArtTitle(articleshortdescription.getArtTitle());
		articleshortdescriptionToUpdate.setArtShortDescrp(articleshortdescription.getArtShortDescrp());
		articleshortdescriptionToUpdate.setArtCarousel(articleshortdescription.getArtCarousel());
                articleshortdescriptionToUpdate.setArticle(articleshortdescription.getArticle());
		
                getCurrentSession().update(articleshortdescriptionToUpdate);
	}

	@Override
	public void delete(Articleshortdescription articleshortdescription) {
		log.info("------------------DELETE-------------");
		Articleshortdescription articledescripDelete=getArticleshortdescription(articleshortdescription.getId());
		if (articledescripDelete != null)
			getCurrentSession().delete(articledescripDelete);
	}
	
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Articleshortdescription> getArticleshortdescriptionForCarousel(){
		return getCurrentSession().createQuery("from Articleshortdescription where artCarousel = 'B' or artCarousel = 'C' order by id desc").setMaxResults(5).list();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	 public List<Articleshortdescription> getWeeklyArticlesByArtViewcount(int limitSize){
		Utility utility = new Utility();
		Date []theWeek = utility.getBackwardWeekForDbStartingFromToday();
		 List<Articleshortdescription> list = getCurrentSession().createQuery("from Articleshortdescription where article.artPublishDate between :startDate and :endDate order by article.artViewcount desc")
				 .setParameter("startDate",theWeek[1])
				 .setParameter("endDate",theWeek[0])
		            .setMaxResults(limitSize)
		            .list();
		        return list;
	 }
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Articleshortdescription> getArticleshortdescriptionForLatestNews(){
		return getCurrentSession().createQuery("from Articleshortdescription where artCarousel= :latestNews or artCarousel= :bothField order by id desc")
				.setString("latestNews", "L")
				.setString("bothField", "B").setMaxResults(12).list();
	}
	
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Articleshortdescription> getArticleshortdescriptionByArticleCategory(String catCode){
		return getCurrentSession().createQuery("from Articleshortdescription where article.articlecategory.catCode = :catCode order by id desc")
		.setString("catCode", catCode).setMaxResults(4).list();
	}
	
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Articleshortdescription> getArticleshortdescriptionByArticleCategory(String catCode, int limitSize){
		return getCurrentSession().
                        createQuery("from Articleshortdescription where article.articlecategory.catCode = :catCode order by id desc").setMaxResults(limitSize)
		.setString("catCode", catCode).list();
	}
	
	
	@Override
	@SuppressWarnings("unchecked")
	public Articleshortdescription getArticleshortdescriptionByArticleId(Article article){
		List<Articleshortdescription> list =  getCurrentSession().createQuery("from Articleshortdescription where article.id = :id")
		.setString("id", article.getId().toString()).list();
		
                if(list != null){
                    if(!list.isEmpty()){
                        return list.get(0);
                    }else{
			return null;
                    }     
                }else{
                    return null;
                }
                
	}
	
	

	@Override
	public Articleshortdescription getArticleshortdescription(Integer id) {
		Articleshortdescription articleshortdescription = (Articleshortdescription) getCurrentSession().get(Articleshortdescription.class, id);
		return articleshortdescription;
	}

	@Override
	public List<Articleshortdescription> selectByArtLangIdntfy(
			String artLangIdntfy) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public long rowCountByArticlecategory(String catCode){
		return (long) getCurrentSession().createQuery("select count(*) from Articleshortdescription where article.articlecategory.catCode = :catCode order by id desc").setString("catCode", catCode).uniqueResult();
	}

	
	@Override
	@SuppressWarnings("unchecked")
	public List<Articleshortdescription> selectByArticlecategory(Articlecategory articlecategory, Integer startPage, Integer pageSize) {
		/*
		
		return getCurrentSession().createCriteria(Articleshortdescription.class, "art")
		.setProjection(Projections.projectionList()		
				.add(Projections.property("art.article.articlecategory.catCode")))
				.setResultTransformer(new AliasToBeanResultTransformer(Articleshortdescription.class)).list();
		*/

		
		  /** 
		   * Selected a detached criteria so we do not need a session to run it 
		   * within. 
		   */  
		  DetachedCriteria criteria = DetachedCriteria.forClass(Articleshortdescription.class);  
		  
		  /** 
		   * Here we are doing an inner join with the Villain table in order to do 
		   * a name comparison with the villainName passed in as a method 
		   * parameter 
		   */  
		  DetachedCriteria article = criteria.createCriteria("article");  
		  criteria.addOrder(Order.desc("id"));
		  article.add(Restrictions.eq("articlecategory",  articlecategory));  
		  
		 
		  
		  article.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		  
		  return (List<Articleshortdescription>) hibernateTemplate.findByCriteria(criteria, startPage * pageSize, pageSize);
		
		//https://forum.hibernate.org/viewtopic.php?f=1&t=1004293
	}

	@Override
	public List<Articleshortdescription> selectAllArticleshortdescription() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	@SuppressWarnings("unchecked")
	public List<Articleshortdescription> getArticleshortdescriptionForLatestNews(Integer startPage, Integer pageSize) {
		return getCurrentSession().createQuery("from Articleshortdescription order by article.id desc").setFirstResult(startPage * pageSize).setMaxResults(pageSize).list();
	}


	@Override
	public Long getRowCount() {
		return (Long) getCurrentSession().createCriteria(Articleshortdescription.class).setProjection(Projections.rowCount()).uniqueResult();
	}

    @Override
    public List<Articleshortdescription> getArticleshortdescriptionServiceByReadCount(Integer startPage, Integer pageSize) {
        return getCurrentSession().createQuery("from Articleshortdescription order by article.artViewcount desc").setFirstResult(startPage * pageSize).setMaxResults(pageSize).list();
    }

}
