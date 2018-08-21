package com.kefet.dao;

import java.util.List;

import com.kefet.model.ImageImagealbum;
import com.kefet.model.Imagealbum;


public interface ImageImagealbumDAO {
	
	 void save(ImageImagealbum imageImagealbum);
    
  //  public void update(ImageImagealbum imageImagealbum);
    
     void delete(ImageImagealbum imageImagealbum);
    
     List<ImageImagealbum> selectByImagealbum(Imagealbum imagealbum);
    
     List<ImageImagealbum> selectByImagealbumSelectedAlbum();
    
  //  public Imagealbum getImageImagealbumId(ImageImagealbumId id );
}
