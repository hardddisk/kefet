package com.kefet.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.search.FullTextQuery;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.jsoup.Jsoup;

import com.kefet.dao.SearchDAO;
import com.kefet.model.Article;
import com.kefet.utility.model.UIArticle;

@Repository
public class SearchDAOImpl implements SearchDAO , Serializable  {
	
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
	public void doIndex() {
	
		FullTextSession fullTextSession = Search.getFullTextSession(getCurrentSession());
        try {
			fullTextSession.createIndexer().startAndWait();
		} catch (InterruptedException e) {
			
			log.error("IN--doIndex---InterruptedException----"+e);
		}
         
        fullTextSession.close();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<UIArticle> search(String word, int firstResult, int maxResult) {
		
		
		
		
	//	Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_36);
		FullTextSession fullTextSession = Search.getFullTextSession(getCurrentSession());
		
		// get a query builder
        QueryBuilder queryBuilder = fullTextSession.getSearchFactory()
        		.buildQueryBuilder().forEntity(Article.class).get();        
   
        // build the query
        org.apache.lucene.search.Query query = queryBuilder.keyword().
        		onFields("artTitle","artContent")
        		.matching(word).createQuery();

       FullTextQuery fullTextQuery = fullTextSession.createFullTextQuery(query, Article.class);
        
       
       fullTextQuery.setFirstResult(firstResult);
       fullTextQuery.setMaxResults(maxResult);
       
        // wrap Lucene query in a javax.persistence.Query
       // org.hibernate.Query fullTextQuery = fullTextSession.createFullTextQuery(query, Article.class);
         
        List<Article> searchResultList =  fullTextQuery.list();  
        
        List<UIArticle> searchResult = new ArrayList<UIArticle>();
      

        for (int i=0; i<searchResultList.size(); i++){
        	UIArticle uIArticle= new UIArticle();
        	Pattern tittle = Pattern.compile(".{0,50}"+word+".{0,50}");
        	Pattern content = Pattern.compile(".{0,50}"+word+".{0,50}");
        	// match only text that are not html
    		Matcher tittleMatch = tittle.matcher(html2text(searchResultList.get(i).getArtTitle()));
    		Matcher artContentMatch = content.matcher(html2text(searchResultList.get(i).getArtContent()));
    		if(tittleMatch.find()){
    			uIArticle.setId(searchResultList.get(i).getId());
    			uIArticle.setArtTitle(searchResultList.get(i).getArtTitle());
    			uIArticle.setArtContent(searchResultList.get(i).getArtTitle());
    			// put back to the content body what was found so the user can see it on the browser.
    			searchResult.add(uIArticle);
    		}if(artContentMatch.find()){
    			uIArticle.setId(searchResultList.get(i).getId());
    			uIArticle.setArtTitle(searchResultList.get(i).getArtTitle());
    		
    			uIArticle.setArtContent(makeBoldAString(word, artContentMatch.group()));
    			// put back to the content body what was found so the user can see it on the browser.
    			searchResult.add(uIArticle);
    		}
        }      
        return searchResult;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public long searchCount(String word){
		
	//	Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_36);
		FullTextSession fullTextSession = Search.getFullTextSession(getCurrentSession());
		
		// get a query builder
        QueryBuilder queryBuilder = fullTextSession.getSearchFactory()
        		.buildQueryBuilder().forEntity(Article.class).get();        
   
        // build the query
        org.apache.lucene.search.Query query = queryBuilder.keyword().
        		onFields("artTitle","artContent")
        		.matching(word).createQuery();

       FullTextQuery fullTextQuery = fullTextSession.createFullTextQuery(query, Article.class);     
       
        // wrap Lucene query in a javax.persistence.Query
       // org.hibernate.Query fullTextQuery = fullTextSession.createFullTextQuery(query, Article.class);
         
        List<Article> searchResultList =  fullTextQuery.list(); 
        
        return searchResultList.size();
	}
	
	protected String makeBoldAString(String stringToBeBold, String totalString ){
		
		int begining =totalString.indexOf(stringToBeBold);
		int totalLenght = stringToBeBold.length();
		totalLenght=begining+totalLenght+3;
		StringBuffer sb = new StringBuffer(totalString);
		sb.insert(begining, "<b>");
		sb.insert(totalLenght, "</b>");
		return sb.toString();
	}
	
	public static String html2text(String html) {
	    return Jsoup.parse(html).text();
	}
}
