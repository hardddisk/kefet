/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kefet.dao.impl;

import com.kefet.dao.HomeDAO;
import com.kefet.model.HomeArticleVideo;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author hardddisk
 */
@Repository
public class HomeDAOImpl implements HomeDAO {

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
    public List<HomeArticleVideo> getArticleAndVideo(Integer startPage, Integer pageSize) {

        List<HomeArticleVideo> myList =  getCurrentSession()
        .createSQLQuery(queryArticleAndVideo())
        .addScalar("id")
        .addScalar("tableName")
        .addScalar("title")
        .addScalar("urlName")
        .addScalar("image")
        .addScalar("catCode")
        .addScalar("viewed")
        .addScalar("timeStamp").setFirstResult(startPage * pageSize).setMaxResults(pageSize)
        .setResultTransformer( Transformers.aliasToBean(HomeArticleVideo.class)).list();
              
        List<HomeArticleVideo> distinctElements = myList.stream().filter(distinctByKey(p -> p.getUrlName())).collect(Collectors.toList());
        
        return distinctElements;

    }
    
    
    
    @Override
    public List<HomeArticleVideo> getArticleAndVideoSpecial() {

        List<HomeArticleVideo> myList =  getCurrentSession()
        .createSQLQuery(querySpecial())
        .addScalar("id")
        .addScalar("tableName")
        .addScalar("title")
        .addScalar("urlName")
        .addScalar("image")
        .addScalar("catCode")
        .addScalar("timeStamp").setMaxResults(3)
        .setResultTransformer( Transformers.aliasToBean(HomeArticleVideo.class)).list();
              
        List<HomeArticleVideo> distinctElements = myList.stream().filter(distinctByKey(p -> p.getUrlName())).collect(Collectors.toList());
        
        return distinctElements;

    }
    
    public <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
    
    private String queryArticleAndVideo(){
        return "select a.ID as id, " +
                        "'article' as tableName, " +
                        "a.ART_TITLE as title, " +
 			"a.ID as urlName, " +
			"ai.ART_IMGSRC as image, " +
                        "a.ID as catCode, "+
                        "a.ART_VIEWCOUNT as viewed, "+
            "a.ART_PUBLISH_DATE as timeStamp " +
        "from ARTICLE a, ARTICLEIMAGE ai,ARTICLE_ARTICLEIMAGE aai " +
        "where a.ID=aai.ART_ID " +
            "AND aai.ART_IMAGE_ID=ai.ID " +
            "union " +
            "select v.ID as id, " +
            "'video' as tableName, " +
            "v.VIDEO_TITLE as title, " +
            "v.VIDEO_URLNAME as urlName, " +
            "v.VIDEO_THUMBNAIL as image, " +
            "vc.CATEGORY_CODE as catCode, "+
            "v.VIDEO_VIEWED as viewed, "+
            "v.VIDEO_DATE as timeStamp " +
        "from VIDEO v, VIDEO_CATEGORY v_c, VIDEOCATEGORY vc " +
            "where v.id=v_c.VIDEO_ID " +
            "AND vc.ID=v_c.VIDEOCATEGORY_ID "+
            "order by timeStamp desc";
    }
    
    private String querySpecial(){
               return "select a.ID as id, " +
                        "'article' as tableName, " +
                        "a.ART_TITLE as title, " +
 			"a.ID as urlName, " +
			"ai.ART_IMGSRC as image, " +
                        "a.ID as catCode, "+              
            "a.ART_PUBLISH_DATE as timeStamp " +
        "from ARTICLE a, ARTICLEIMAGE ai,ARTICLE_ARTICLEIMAGE aai " +
        "where a.ID=aai.ART_ID " +
            "AND aai.ART_IMAGE_ID=ai.ID " +
            "AND a.ART_SPECIAL = '1' "+
            "union " +
            "select v.ID as id, " +
            "'video' as tableName, " +
            "v.VIDEO_TITLE as title, " +
            "v.VIDEO_URLNAME as urlName, " +
            "v.VIDEO_THUMBNAIL as image, " +
            "vc.CATEGORY_CODE as catCode, "+
            "v.VIDEO_DATE as timeStamp " +
        "from VIDEO v, VIDEO_CATEGORY v_c, VIDEOCATEGORY vc " +
            "where v.id=v_c.VIDEO_ID " +
            "AND vc.ID=v_c.VIDEOCATEGORY_ID "+
            "AND v.VIDEO_SPECIAL='1' "+
            "order by timeStamp desc";
        
    }
    
 
}
