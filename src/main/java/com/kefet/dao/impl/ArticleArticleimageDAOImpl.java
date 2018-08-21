package com.kefet.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kefet.dao.ArticleArticleimageDAO;
import com.kefet.model.ArticleArticleimage;
import com.kefet.model.ArticleArticleimageId;



@Repository
public class ArticleArticleimageDAOImpl implements ArticleArticleimageDAO {

	
	private final Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	
	
	@Override
	public void save(ArticleArticleimage articleArticleimage) {
		getCurrentSession().save(articleArticleimage);
	}

	/*
	
	@Override
	public void update(ArticleArticleimage articleArticleimage) {
		ArticleArticleimage articleArticleimageToUpdate = getArticleArticleimage(articleArticleimage.getId());
		strategyToUpdate.setName(strategy.getName());
		strategyToUpdate.setType(strategy.getType());
		getCurrentSession().update(strategyToUpdate);
	}
	*/

	/*
	@Override
	public ArticleArticleimage getArticleArticleimage(ArticleArticleimageId id,
			Article article, Articleimage articleimage) {
		// TODO Auto-generated method stub
		return null;
	}
	*/
	
	 public void delete(ArticleArticleimage articleArticleimage){
		// TODO Auto-generated method stub
	 }
	

	@Override
	public boolean delete(String artId) {
		
		boolean success = true; 
    	getCurrentSession().beginTransaction();
		 try{
			 Query query = getCurrentSession().createQuery("DELETE from  ArticleArticleimage where article.id = :id").setString("id", artId);
                         query.executeUpdate();
                         getCurrentSession().getTransaction().commit();
		 }catch(Exception e){
			 getCurrentSession().getTransaction().rollback();
			log.error("ArticleArticleimageDAOImpl -- not able to delete");
			success = false;
		 }finally{
			 getCurrentSession().close();
		 }
		 
		 return success;
		
		
	}
	
	
	@Override
	@SuppressWarnings("unchecked")
	public List<ArticleArticleimage> selectByArticleId(Integer artId){
		
	    List<ArticleArticleimage> articleArticleimageList = getCurrentSession().createQuery("from ArticleArticleimage where ArticleArticleimageId.artId = :artId")
	            .setParameter("artId", artId)
	            .list();
	       
		return articleArticleimageList;
		
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<ArticleArticleimage> selectByArtImageId(Integer artImageId){
		
	    List<ArticleArticleimage> articleArticleimageList = getCurrentSession().createQuery("from ArticleArticleimage where ArticleArticleimageId.artImageId = :artImageId")
	            .setParameter("artImageId", artImageId)
	            .list();
		return articleArticleimageList;
		
	}
	
	

	/*
	@Override
	public ArticleArticleimage getArticleArticleimage(ArticleArticleimageId id) {
	    List<Users> list = getCurrentSession().createQuery("from ArticleArticleimage where userUname = :userUname")
	            .setParameter("userUname", userUname)
	            .list();
	        return list.size() > 0 ?(Users)list.get(0): null;
	}
*/

	@Override
	public ArticleArticleimage getArticleArticleimage(Integer artId) {
		ArticleArticleimage articleArticleimage = (ArticleArticleimage) getCurrentSession().get(ArticleArticleimageId.class, artId);
		return articleArticleimage;
	}



	@Override
	public void update(ArticleArticleimage articleArticleimage) {
		// TODO Auto-generated method stub
		
	}





	@Override
	public ArticleArticleimage getArticleArticleimage(Integer artId,
			Integer artImageId) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	@SuppressWarnings("unchecked")
	public ArticleArticleimage getArticleArticleimage(ArticleArticleimageId id) {
	    List<ArticleArticleimage> list = getCurrentSession().createQuery("from ArticleArticleimage where ArticleArticleimageId.artId = :artId and ArticleArticleimageId.artImageId =:artImageId")
	            .setParameter("artId", id.getArtId()).setParameter("artImageId", id.getArtImageId())
	            .list();
	        return list.size() > 0 ?(ArticleArticleimage)list.get(0): null;
	}

}
