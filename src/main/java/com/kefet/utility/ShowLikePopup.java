package com.kefet.utility;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

@Component
public class ShowLikePopup {
	
	
	public boolean showLikePopup(HttpServletRequest request){
		HttpSession session = request.getSession();
		if(session.isNew()){
			return true;
		}else{
			return false;
		}	
	}
}
