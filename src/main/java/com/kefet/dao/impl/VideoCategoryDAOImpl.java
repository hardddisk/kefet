package com.kefet.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Date;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.kefet.dao.VideoCategoryDAO;
import com.kefet.model.Video;
import com.kefet.model.VideoCategory;
import com.kefet.model.VideoCategoryId;
import com.kefet.model.Videocategory;
import com.kefet.utility.Utility;


@Repository
public class VideoCategoryDAOImpl implements VideoCategoryDAO , Serializable  {
	
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
	public void save(VideoCategory videoCategory) {
		getCurrentSession().save(videoCategory);
	}

	@Override
	public void update(VideoCategory videoCategory) {
		VideoCategory videoCategoryToUpdate = getVideoCategory(videoCategory.getId());
		videoCategoryToUpdate.setVideo(videoCategory.getVideo());
		videoCategoryToUpdate.setVideocategory(videoCategory.getVideocategory());
		getCurrentSession().update(videoCategoryToUpdate);
	}

	@Override
	public void delete(VideoCategory videoCategory) {
		VideoCategory videoCategoryToDel=getVideoCategory(videoCategory.getId());
		if (videoCategoryToDel != null)
			getCurrentSession().delete(videoCategoryToDel);
	}
	
	@Override
	public VideoCategory getVideoCategory(VideoCategoryId id){
		VideoCategory videoCategory = (VideoCategory) getCurrentSession().get(VideoCategoryId.class, id);
		return videoCategory;	
	}
	
	
	@Override
	public long rowCountByVideocategory(String categoryCode){
		return (long) getCurrentSession().createQuery("select count(*) from VideoCategory where videocategory.categoryCode = :categoryCode order by id desc").setString("categoryCode", categoryCode).uniqueResult();
	}	
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Video> getVideoByVideocategory(Videocategory videocategory, String sorting, Integer startPage, Integer pageSize) {
            
            log.debug("(((((((((((((((((((TTT)))))))))))))))))))"+startPage* pageSize);
       
            log.debug("(((((((((((((((((((TTT)))))))))))))))))))"+pageSize);
            
            String query="";
            
            if(sorting.equals("Date")){
                
                query="Select v from Video v, VideoCategory vc where v.id = vc.video.id and vc.videocategory.id =:id order by v.id desc";
                
            }if(sorting.equals("Title")){
                
                query="Select v from Video v, VideoCategory vc where v.id = vc.video.id and vc.videocategory.id =:id order by v.videoTitle desc";
                
            }if(sorting.equals("Views")){
                
                query="Select v from Video v, VideoCategory vc where v.id = vc.video.id and vc.videocategory.id =:id order by v.videoViewed desc";
                
            }
            
            
            
	    return  getCurrentSession().createQuery(query)
	            .setParameter("id",videocategory.getId()).setFirstResult(
	            		startPage * pageSize).setMaxResults(pageSize).list();
            	
		//https://forum.hibernate.org/viewtopic.php?f=1&t=1004293
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Video> getMostViewedVideosByVideocategory(Videocategory videocategory){
		return  getCurrentSession().createQuery("Select v from Video v, VideoCategory vc where v.id = vc.video.id and vc.videocategory.id =:id order by v.videoViewed desc")
	            .setParameter("id",videocategory.getId()).setMaxResults(6).list();
		
	}
	

	@Override
	@SuppressWarnings("unchecked")
	public List<Video> getLastVideosByCategoryCode(String categoryCode, int maxResult){
		return  getCurrentSession().createQuery("Select v from Video v, "
								 + "VideoCategory vc, "
								 + "Videocategory vidcat "
							+ "where v.id = vc.video.id and "
							+ "vidcat.id=vc.videocategory.id and "
							+ "vidcat.categoryCode=:categoryCode order by v.id desc")
	            .setParameter("categoryCode",categoryCode).setMaxResults(maxResult).list();
		
	}

	
	@Override
	@SuppressWarnings("unchecked")
	
	/*
	 select vc.* 
from VIDEO v, VIDEO_CATEGORY vc, VIDEOCATEGORY cat
where v.ID=vc.VIDEO_ID
and cat.CATTYPE != 'mo'
and cat.id=vc.VIDEOCATEGORY_ID 
group by v.ID order by v.id desc;
	 */
	public List<VideoCategory> getLatest10Videos(){
		return  getCurrentSession().createQuery("Select vc from Video v, VideoCategory vc, Videocategory cat "
							+ "where v.id = vc.video.id and cat.cattype != :cattype "
							+ "and cat.id=vc.videocategory.id group by v.id Order by v.id desc").setString("cattype", "mo").setMaxResults(10).list();
	}
	
	
	@Override
	@SuppressWarnings("unchecked")
	public List<VideoCategory> getLatest9Movies(){
		return  getCurrentSession().createQuery("Select vc from Video v, VideoCategory vc, Videocategory cat "
				+ "where v.id = vc.video.id and cat.cattype = :cattype "
				+ "and cat.id=vc.videocategory.id group by v.id Order by v.id desc").setString("cattype", "mo").setMaxResults(9).list();
	
	}
	
	
	
	@Override
	@SuppressWarnings("unchecked")
	public List<VideoCategory> getMost6WatchedMovie(){
		return  getCurrentSession().createQuery("Select vc from Video v, VideoCategory vc "
				+ "where v.id = vc.video.id AND vc.videocategory.cattype=:cattype group by v.videoViewed Order by v.videoViewed desc").setString("cattype", "mo").setMaxResults(6).list();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<VideoCategory> get4TvShowsForHeader(){
		return  getCurrentSession().createQuery("Select vc from Video v, VideoCategory vc "
				+ "where v.id = vc.video.id AND vc.videocategory.cattype=:cattype group by v.videoViewed Order by v.videoViewed desc").setString("cattype", "tv").setMaxResults(4).list();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<VideoCategory> getMost6WatchedVideo(){
		Utility utility = new Utility();
		Date []theMonth = utility.getBackwardMonthForDbStartingFromToday();
		return  getCurrentSession().createQuery("Select vc from Video v, VideoCategory vc "
				+ "where v.id = vc.video.id and v.videoDate between :startDate and :endDate group by v.videoViewed Order by v.videoViewed desc")
				 .setParameter("startDate",theMonth[1])
				 .setParameter("endDate",theMonth[0])
				 .setMaxResults(6).list();

	}
	
	
	
	@Override
	public long getMostWatchedVideoRowCount(String cat){
            
            String query ="";
            
            if(cat.equals("all")){
                
                query="Select count(v.id) from Video v Order by v.videoViewed desc";
                
            }else{
                query="Select count(vc.video.id) from VideoCategory vc, Video v where vc.video.id = v.id Order by v.videoViewed desc";
            }
		
            return (long) getCurrentSession().createQuery(query).uniqueResult();      
                
	}

	
	@Override
	@SuppressWarnings("unchecked")
	public List<VideoCategory> getMostWatchedVideo(String categoryCode, Integer startPage, Integer pageSize) {
	    return getCurrentSession()
	    		.createQuery("Select vc from Video v, VideoCategory vc "
				+ "where v.id = vc.video.id and vc.videocategory.categoryCode=:categoryCode group by v.videoViewed Order by v.videoViewed desc").setString("categoryCode", categoryCode)
				.setFirstResult(startPage * pageSize).setMaxResults(pageSize).list();	    
		//https://forum.hibernate.org/viewtopic.php?f=1&t=1004293
	}
        
        @Override
	@SuppressWarnings("unchecked")
        public List<VideoCategory> getAllCatMostWatchedVideo(Integer startPage, Integer pageSize){
            return getCurrentSession()
	    		.createQuery("Select vc from Video v, VideoCategory vc "
				+ "where v.id = vc.video.id group by v.videoViewed Order by v.videoViewed desc")
				.setFirstResult(startPage * pageSize).setMaxResults(pageSize).list();	
        }
	
	@Override
	@SuppressWarnings("unchecked")
	public List<VideoCategory> getMostWatched4Video() {
	    return getCurrentSession()
	    		.createQuery("Select vc from Video v, VideoCategory vc "
				+ "where v.id = vc.video.id group by v.videoViewed Order by v.videoViewed desc").setMaxResults(4).list();
					    
		//https://forum.hibernate.org/viewtopic.php?f=1&t=1004293
	}
	
}
