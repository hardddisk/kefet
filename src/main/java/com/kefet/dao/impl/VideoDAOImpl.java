package com.kefet.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.kefet.dao.VideoDAO;
import com.kefet.model.HomeArticleVideo;
import com.kefet.model.Video;
import com.kefet.model.VideoCategory;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.hibernate.transform.Transformers;

@Repository
public class VideoDAOImpl implements VideoDAO, Serializable {

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
    public void save(Video video) {
        getCurrentSession().save(video);
    }

    @Override
    public void update(Video video) {
        Video videoToUpdate = getVideo(video.getId());
        videoToUpdate.setVideoActve(video.getVideoActve());
        videoToUpdate.setVideoAgeLmt(video.getVideoAgeLmt());
        videoToUpdate.setVideoCategories(video.getVideoCategories());
        videoToUpdate.setVideoDescrip(video.getVideoDescrip());
        videoToUpdate.setVideoTitle(video.getVideoTitle());
        videoToUpdate.setVideoLocation(video.getVideoLocation());
        videoToUpdate.setVideoRatingUp(video.getVideoRatingUp());
        videoToUpdate.setVideoRatingDown(video.getVideoRatingDown());
        videoToUpdate.setVideoUrlname(video.getVideoUrlname());
        videoToUpdate.setVideoViewed(video.getVideoViewed());
        getCurrentSession().update(videoToUpdate);
    }

    @Override
    public void delete(Video video) {
        Video videoToDel = getVideo(video.getId());
        if (videoToDel != null) {
            getCurrentSession().delete(videoToDel);
        }
    }

    @Override
    public Video getVideo(java.lang.Integer id) {
        Video video = (Video) getCurrentSession().get(Video.class, id);
        return video;
    }

    @Override
    public boolean diactivateVideo(Video video, Boolean videoActve) {
        boolean success = true;
        getCurrentSession().beginTransaction();
        try {
            Query query = getCurrentSession().createQuery("update Video set videoActve = :videoActve"
                    + " where id = :id").setString("id", video.getId().toString()).setString("videoActve", videoActve.toString());
            query.executeUpdate();
            getCurrentSession().getTransaction().commit();
        } catch (Exception e) {
            getCurrentSession().getTransaction().rollback();
            log.error("VideoDAOImpl -- not able to diactivateVideo");
            success = false;
        } finally {
            getCurrentSession().close();
        }

        return success;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Video getVideoByVideoUrlname(String videoUrlname) {
        List<Video> list = getCurrentSession().createQuery("from Video where videoUrlname=:videoUrlname").setString("videoUrlname", videoUrlname).list();
        return list.size() > 0 ? (Video) list.get(0) : null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Video> getNew9VideoForMenuByDate() {
        return getCurrentSession().createQuery("from Video v, VideoCategory vc where v.id=vc.video.id order by v.videoDate desc").setMaxResults(9).list();
    }

    @Override
    public List<HomeArticleVideo> getLatestVideo(Integer size) {

        List<HomeArticleVideo> myList = getCurrentSession()
                .createSQLQuery(queryString(""))
                .addScalar("id")
                .addScalar("tableName")
                .addScalar("title")
                .addScalar("urlName")
                .addScalar("image")
                .addScalar("catCode")
                .addScalar("viewed")
                .addScalar("timeStamp").setMaxResults(size)
                .setResultTransformer(Transformers.aliasToBean(HomeArticleVideo.class)).list();

        List<HomeArticleVideo> distinctElements = myList.stream().filter(distinctByKey(p -> p.getUrlName())).collect(Collectors.toList());

        return distinctElements;

    }

    @Override
    public long rowCountByVideo() {
        return (long) getCurrentSession().createQuery("select count(*) from Video order by id desc").uniqueResult();
    }

    @Override
    public List<HomeArticleVideo> getAllLatestVideo(String sorting, Integer startPage, Integer pageSize) {

        List<HomeArticleVideo> myList = getCurrentSession()
                .createSQLQuery(queryString(sorting))
                .addScalar("id")
                .addScalar("tableName")
                .addScalar("title")
                .addScalar("urlName")
                .addScalar("image")
                .addScalar("location")
                .addScalar("catCode")
                .addScalar("viewed")
                .addScalar("timeStamp").setFirstResult(startPage * pageSize).setMaxResults(pageSize)
                .setResultTransformer(Transformers.aliasToBean(HomeArticleVideo.class)).list();

        List<HomeArticleVideo> distinctElements = myList.stream().filter(distinctByKey(p -> p.getUrlName())).collect(Collectors.toList());

        return distinctElements;

    }

    public <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    private String queryString(String sorting) {

        String query
                = "select v.ID as id, "
                + "'video' as tableName, "
                + "v.VIDEO_TITLE as title, "
                + "v.VIDEO_URLNAME as urlName, "
                + "v.VIDEO_THUMBNAIL as image, "
                + "v.VIDEO_LOCATION as location, "
                + "vc.CATEGORY_CODE as catCode, "
                + "v.VIDEO_VIEWED as viewed, "
                + "v.VIDEO_DATE as timeStamp "
                + "from VIDEO v, VIDEO_CATEGORY v_c, VIDEOCATEGORY vc "
                + "where v.id=v_c.VIDEO_ID "
                + "AND vc.ID=v_c.VIDEOCATEGORY_ID ";

        if (sorting.equals("Today")) {

            sorting = "AND DATE(v.VIDEO_DATE) = CURDATE() order by timeStamp desc";

            return query += sorting;

        }
        if (sorting.equals("Yesterday")) {

            sorting = "AND DATE(v.VIDEO_DATE) = CURDATE()  - INTERVAL 1 DAY order by timeStamp desc";

            return query += sorting;
        }
        if (sorting.equals("This Month")) {

            sorting = "AND v.VIDEO_DATE >= LAST_DAY(CURRENT_DATE) + INTERVAL 1 DAY - INTERVAL 1 MONTH "
                    + "AND v.VIDEO_DATE < LAST_DAY(CURRENT_DATE) + INTERVAL 1 DAY order by timeStamp desc";

            return query += sorting;
        } else {
            query += "order by timeStamp desc";
        }

        log.debug("################################");
        log.debug("##########query#################" + query);
        log.debug("################################");

        return query;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Integer getMaxId() {
        int retultRet = 0;
        List<Integer> list = null;

        list = getCurrentSession().createQuery("select Max(id) from Video").list();
        if (list != null) {
            if (!list.isEmpty()) {
                if (list.get(0) != null) {
                    retultRet = list.get(0);
                }
            }
        }
        return retultRet;
    }
    


    @Override
    public List<VideoCategory> getTVDramaByCategoryCode(String categoryCode, Integer startPage, Integer pageSize) {
        
        String query = "select v_c from Video v, Videocategory vc, VideoCategory v_c "
                      +"where v.id = v_c.video.id "
                      +"and v_c.videocategory.id = vc.id "
                      +"and vc.categoryCode = :categoryCode "
                      +"and vc.cattype=:cattype order by v.id desc";
        
        return getCurrentSession()
                .createQuery(query)
                .setString("cattype", "tv_drm")
                .setString("categoryCode", categoryCode)
                .setFirstResult(startPage * pageSize)
                .setMaxResults(pageSize).list();
     
    }

        @Override
    public Long countTVDramaByCategoryCode(String categoryCode){
        
        String query = "select v_c from Video v, Videocategory vc, VideoCategory v_c "
                      +"where v.id = v_c.video.id "
                      +"and v_c.videocategory.id = vc.id "
                      +"and vc.categoryCode = :categoryCode "
                      +"and vc.cattype=:cattype ";
       
             List<VideoCategory> list = getCurrentSession()
                .createQuery(query)
                .setString("cattype", "tv_drm")
                .setString("categoryCode", categoryCode).list();
       

        return (long) list.size();
    }
    
    
    @Override
    public Long countAllTVDrama() {
               String query = "select v_c from Video v, Videocategory vc, VideoCategory v_c "
                      +"where v.id = v_c.video.id "
                      +"and v_c.videocategory.id = vc.id "                    
                      +"and vc.cattype=:cattype ";
       
             List<VideoCategory> list = getCurrentSession()
                .createQuery(query)
                .setString("cattype", "tv_drm")
                .list();
             
             
             return (long) list.size();
    }

    @Override
    public List<VideoCategory> getAllTVDrama(Integer startPage, Integer pageSize) {
                
        String query = "select v_c from Video v, Videocategory vc, VideoCategory v_c "
                      +"where v.id = v_c.video.id "
                      +"and v_c.videocategory.id = vc.id "
                      +"and vc.cattype=:cattype order by v.id desc";
        
        return getCurrentSession()
                .createQuery(query)
                .setString("cattype", "tv_drm")
                .setFirstResult(startPage * pageSize).setMaxResults(pageSize).list();
    }
}
