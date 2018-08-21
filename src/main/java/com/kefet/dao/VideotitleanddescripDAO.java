/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kefet.dao;

import com.kefet.model.Videotitleanddescrip;
import com.kefet.utility.model.UIVideo;

/**
 *
 * @author hardddisk
 */
public interface VideotitleanddescripDAO {
    
     void save(Videotitleanddescrip videotitleanddescrip);
    
     void update(Videotitleanddescrip videotitleanddescrip);
    
     Videotitleanddescrip getVideotitleanddescrip(Integer videoId, String videoLang);
    
     void delete(Integer videoId);
    
     Videotitleanddescrip getVideo(String videoUrlname);
    
}
