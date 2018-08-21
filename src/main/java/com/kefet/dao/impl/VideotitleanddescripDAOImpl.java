/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kefet.dao.impl;


import com.kefet.dao.VideotitleanddescripDAO;
import com.kefet.model.Videotitleanddescrip;
import java.io.Serializable;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author hardddisk
 */
@Repository
public class VideotitleanddescripDAOImpl implements VideotitleanddescripDAO , Serializable  {
	
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
    public void save(Videotitleanddescrip videotitleanddescrip) {
        getCurrentSession().save(videotitleanddescrip);
    }

    @Override
    public void update(Videotitleanddescrip videotitleanddescrip) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Videotitleanddescrip getVideotitleanddescrip(Integer videoId, String videoLang) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer videoId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Videotitleanddescrip getVideo(String videoUrlname) {
        List<Videotitleanddescrip> list= getCurrentSession().createQuery("select v.video, v.id "
                + "from Videotitleanddescrip v "
                + "where v.video.videoUrlname=:videoUrlname "
                + "and v.video.id=v.id.videoId "
                + "and v.id.videoLang=:videoLang")
                .setString("videoUrlname", videoUrlname)
                .setString("videoLang", "E").list();
        
        return list.size() > 0 ?(Videotitleanddescrip)list.get(0): null;
    }

}
