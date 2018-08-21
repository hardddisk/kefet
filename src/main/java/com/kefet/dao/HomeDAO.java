/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kefet.dao;

import com.kefet.model.HomeArticleVideo;
import java.util.List;

/**
 *
 * @author hardddisk
 */
public interface HomeDAO {
    
    
    List<HomeArticleVideo> getArticleAndVideo(Integer startPage, Integer pageSize);
    
    
    List<HomeArticleVideo> getArticleAndVideoSpecial();
    
    
}
