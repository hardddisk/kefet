package com.kefet.dao;


import java.util.List;

import com.kefet.model.VideoCategory;
import com.kefet.model.VideoCategoryId;
import com.kefet.model.Video;
import com.kefet.model.Videocategory;

 public interface VideoCategoryDAO {	
	
	 void save(VideoCategory videoCategory);

	 void update(VideoCategory videoCategory);

	 void delete(VideoCategory videoCategory);
	
	 VideoCategory getVideoCategory(VideoCategoryId videoCategoryId);
	
	 List<Video> getVideoByVideocategory(Videocategory videocategory, String sorting, Integer startPage, Integer pageSize);
	
	 long rowCountByVideocategory(String categoryCode);
	
	 List<Video> getMostViewedVideosByVideocategory(Videocategory videocategory);
	
	 List<Video> getLastVideosByCategoryCode(String categoryCode, int maxResult);
	
	 List<VideoCategory> getLatest10Videos();
	
	 List<VideoCategory> getLatest9Movies();
	
	 List<VideoCategory> getMost6WatchedVideo();
	
	 List<VideoCategory> getMost6WatchedMovie();
	
	 List<VideoCategory> get4TvShowsForHeader();
	
	 long getMostWatchedVideoRowCount(String cat);
	
	 List<VideoCategory> getMostWatchedVideo(String categoryCode, Integer startPage, Integer pageSize);
        
         List<VideoCategory> getAllCatMostWatchedVideo(Integer startPage, Integer pageSize);
	
	 List<VideoCategory> getMostWatched4Video();
	
	

}
