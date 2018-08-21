package com.kefet.dao;

import com.kefet.model.Image;

public interface ImageDAO {

     void save(Image image);
    
     void update(Image image);
    
     void delete(Image image);
   
     Image getImage(java.lang.Integer id);
    
     Integer getMaxId();
}
