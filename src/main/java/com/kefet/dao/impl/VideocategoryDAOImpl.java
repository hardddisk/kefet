package com.kefet.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kefet.dao.VideocategoryDAO;
import com.kefet.model.Videocategory;
import java.sql.Timestamp;
import java.util.Calendar;



@Repository
public class VideocategoryDAOImpl implements VideocategoryDAO , Serializable  {
	
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
    public void save(Videocategory videocategory) {
        getCurrentSession().save(videocategory);
    }

    @Override
    public void update(Videocategory videocategory) {
            Videocategory videocategoryToUpdate = getVideocategory(videocategory.getId());
            videocategoryToUpdate.setCategoryCode(videocategory.getCategoryCode());
            videocategoryToUpdate.setCategoryDescp(videocategory.getCategoryDescp());
            videocategoryToUpdate.setCattype(videocategory.getCattype());
            videocategoryToUpdate.setVisible(videocategory.getVisible());
            getCurrentSession().update(videocategoryToUpdate);

    }

    @Override
    public void delete(Videocategory videocategory) {
            Videocategory videocategoryToDel=getVideocategory(videocategory.getId());
            if (videocategoryToDel != null)
                    getCurrentSession().delete(videocategoryToDel);
    }

    @Override
    public Videocategory getVideocategory(Short id) {
            Videocategory videocategory = (Videocategory) getCurrentSession().get(Videocategory.class, id);
            return videocategory;	
    }

    @Override
    @SuppressWarnings("unchecked")
    public Videocategory selectByCategoryCode(String categoryCode){
        List<Videocategory> list = getCurrentSession().createQuery("from Videocategory where categoryCode = :categoryCode")
            .setParameter("categoryCode", categoryCode)
            .list();
        return list.size() > 0 ?(Videocategory)list.get(0): null;
    }
    
    
    @Override
    public long getNumberOfVideocategory(){  
        
        return ((long) getCurrentSession().createQuery("select count(*) from Videocategory").uniqueResult());
        
    }
    

    
        
    @Override
    @SuppressWarnings("unchecked")
    public int selectByCategoryCodeLike(String categoryCode){
        List<Videocategory> list = getCurrentSession().createQuery("from Videocategory v where str(v.categoryCode) like :categoryCode")
            .setParameter("categoryCode", categoryCode + "%")
            .list();
        return list.size();        
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Videocategory> getAllVideocategory() {
            return getCurrentSession().createQuery("from Videocategory order by categoryDescp asc").list();
    }
    
    
    @Override
    @SuppressWarnings("unchecked")
    public List<Videocategory> getVideocategories( Integer startPage, Integer pageSize) {
        return  getCurrentSession().createQuery("from Videocategory order by categoryDescp")
            .setFirstResult(startPage * pageSize).setMaxResults(pageSize).list();

            //https://forum.hibernate.org/viewtopic.php?f=1&t=1004293
    }

    @Override
    public int updateVidStat(String categoryCode) {
        
        // 1) create a java calendar instance
        Calendar calendar = Calendar.getInstance();

        // 2) get a java.util.Date from the calendar instance.
        //    this date will represent the current instant, or "now".
        java.util.Date now = calendar.getTime();

        // 3) a java current time (now) instance
        java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());
        
        return getCurrentSession().createQuery("update Videocategory set vidStat = :currentTimestamp" +
    				" where categoryCode = :categoryCode")
                .setParameter("categoryCode", categoryCode)
                .setParameter("currentTimestamp", currentTimestamp)
                .executeUpdate();
    }

    @Override
    public List<Videocategory> getVideocategoryLessThan30H() {
        long now = System.currentTimeMillis();
        long adayOld = now - (40L * 60L * 60L * 1000L);
       // long adayOld = now - (5L * 60L * 1000L); // 5 min for testing
        Timestamp adayOldTimestamp = new Timestamp(adayOld);
       
        return getCurrentSession().createQuery("from Videocategory where vidStat > :date").setTimestamp("date", adayOldTimestamp).list();
 
    }
    
    @Override
    public List<Videocategory> getCategoryByCattype(String cattype){
        
        
        return getCurrentSession().createQuery("from Videocategory where cattype = :cattype").setString("cattype", cattype).list();
        
    }
    
    

}
