package com.kefet.utility;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import com.kefet.model.VideoCategory;
import com.kefet.service.VideoCategoryService;
import com.kefet.service.VideocategoryService;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.springframework.ui.ModelMap;





@Component
public class HeaderInterceptor  extends HandlerInterceptorAdapter {
 
	private final Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	VideoCategoryService videoCategoryService;
        
        @Autowired
	VideocategoryService videocategoryService;
 
    
	@Override
    public boolean preHandle(HttpServletRequest request,
        HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession();

        @SuppressWarnings("unchecked")
        List<VideoCategory> temp = (List<VideoCategory>) session.getAttribute("fourVideos");
        if(temp == null){
            log.debug("##################################################");
            log.debug("##################preHandle#######################");
            log.debug("##################################################");
                session.setAttribute("fourVideos", getNew4VideoForMenuByDate());
        }

        @SuppressWarnings("unchecked")
        List<VideoCategory> tempMovies = (List<VideoCategory>) session.getAttribute("fourMovies");
        if(tempMovies == null){
                session.setAttribute("fourMovies", getNew4MovieForMenuByDate());
        }

        @SuppressWarnings("unchecked")
        List<VideoCategory> tempTvShow = (List<VideoCategory>) session.getAttribute("fourMovies");
        if(tempTvShow == null){
                session.setAttribute("fourTvShows", getNew4TvShowForMenuByDate());
        }


        JSONObject newPrograms = (JSONObject) session.getAttribute("newPrograms");

        if (newPrograms == null) {
            session.setAttribute("newPrograms", videocategoryService.getVideocategoryLessThan30H());
            log.debug("#############################new#####################"+newPrograms);
        }

        return true;
    }
    
    
    
    public void printWelcome(ModelMap model, HttpServletRequest request ) throws IOException {
        
        
   
        
    }
    
	protected List<VideoCategory> getNew4VideoForMenuByDate(){
        return videoCategoryService.getLatest10Videos().subList(0, 4);
	}
	
	protected List<VideoCategory> getNew4MovieForMenuByDate(){
        return videoCategoryService.getLatest9Movies().subList(0, 4);
	}
	
	protected List<VideoCategory> getNew4TvShowForMenuByDate(){
        return videoCategoryService.get4TvShowsForHeader().subList(0, 4);
	}
 
    
     /*
    
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, 
		Object handler, ModelAndView modelAndView
            ) throws Exception {
    	log.debug("(((((((((((((((((((((((postHandle)))))))))))))))))))))))");
        log.debug("(((((((((((((((((((((((postHandle)))))))))))))))))))))))");
    	HttpSession session = request.getSession();
    	session.setAttribute("fourVideos", getNew4VideoForMenuByDate());
       
    }
 
   
    @Override
    public void afterCompletion(HttpServletRequest request,
            HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    	log.debug("(((((((((((((((((((((((afterCompletion)))))))))))))))))))))))");
        log.debug("(((((((((((((((((((((((afterCompletion)))))))))))))))))))))))");
        log.debug("(((((((((((((((((((((((afterCompletion)))))))))))))))))))))))");
        log.debug("(((((((((((((((((((((((afterCompletion)))))))))))))))))))))))"+response.getOutputStream());
        log.debug("(((((((((((((((((((((((afterCompletion)))))))))))))))))))))))");
        log.debug("(((((((((((((((((((((((afterCompletion)))))))))))))))))))))))");
        log.debug("(((((((((((((((((((((((afterCompletion)))))))))))))))))))))))");
    	HttpSession session = request.getSession();
    	session.setAttribute("fourVideos", getNew4VideoForMenuByDate());
    }
    
    */

}
