package com.kefet.dao;

import java.util.List;

import com.kefet.model.Videocategory;

public interface VideocategoryDAO {

    void save(Videocategory videocategory);

    void update(Videocategory videocategory);

    void delete(Videocategory videocategory);

    Videocategory getVideocategory(java.lang.Short id);

    Videocategory selectByCategoryCode(String categoryCode);

    long getNumberOfVideocategory();

    int selectByCategoryCodeLike(String categoryCode);

    List<Videocategory> getAllVideocategory();
    
    List<Videocategory> getVideocategories( Integer startPage, Integer pageSize);
    
    /**
     * This method will update when there is a video on this specific category. The vidStat will get a timestamp that will be useful for the front end 
     * desplay. 
     * @param categoryCode the categoryCode for where clasuse.
     * @return how many rows were affected. 
     */
    int updateVidStat(String categoryCode);
    
    /**
     * This method will retun all the videocategory list that where updated in the last 30 hours.
     * @return list of VideocategoryObject
     */
     List<Videocategory> getVideocategoryLessThan30H();
     
     
     List<Videocategory> getCategoryByCattype(String cattype);

}
