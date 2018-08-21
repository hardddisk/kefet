package com.kefet.dao;


import java.util.List;

import com.kefet.model.Imagealbum;

public interface ImagealbumDAO {
	
	
	 void save(Imagealbum imagealbum);
    
     void update(Imagealbum imagealbum);
    
     void delete(Imagealbum imagealbum);
    
     Imagealbum getImagealbumByAlbumName(String albumName);
    
     List<Imagealbum> getAllImagealbum(Integer startPage, Integer pageSize);
    
     Imagealbum getImagealbum(java.lang.Integer id);
    
    /**
     * 
     * @return the imagealbum objects that does not have a null value on albumSelected column.
     */
     List<Imagealbum> getSelectedImagealbumByAlbumSelected();
    
     Long getRowCount();

}
