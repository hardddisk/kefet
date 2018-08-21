package com.kefet.dao;


import com.kefet.model.HomeArticleVideo;
import java.util.List;

import com.kefet.model.Video;
import com.kefet.model.VideoCategory;
import com.kefet.model.Videocategory;

    public interface VideoDAO {
	
    void save(Video video);

    void update(Video video);

    void delete(Video video);

    Video getVideo(java.lang.Integer id); 

    boolean diactivateVideo(Video video, Boolean videoActve);

    Video getVideoByVideoUrlname(String videoUrlname);

    List<Video> getNew9VideoForMenuByDate();
    
    
     Long countTVDramaByCategoryCode(String categoryCode);
    
     Long countAllTVDrama();
    
    List<VideoCategory> getTVDramaByCategoryCode(String categoryCode, Integer startPage, Integer pageSize);
    
    List<VideoCategory> getAllTVDrama( Integer startPage, Integer pageSize);

     List<HomeArticleVideo> getLatestVideo(Integer size); 

     List<HomeArticleVideo> getAllLatestVideo(String sorting, Integer startPage, Integer pageSize);

    Integer getMaxId();

    /**
     * It return home many video in total exist in VIDEO table.
     * @return long value of the row count.
     */
    long rowCountByVideo();

}
