package com.kefet.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.kefet.utility.model.UIVideo;


@Component
public class VideoValidator implements Validator {

	@Override
	public boolean supports(Class<?> aClass) {
		// TODO Auto-generated method stub
		return UIVideo.class.equals(aClass);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		UIVideo uIVideo = (UIVideo) obj;
		
		
		if(uIVideo.getVideoTitleEnglish().isEmpty() ){
			errors.rejectValue("videoTitleEnglish", "videoTitleEnglishError"); 
		}
                if(uIVideo.getVideoTitleAmharic().isEmpty() ){
			errors.rejectValue("videoTitleAmharic", "videoTitleAmharicError"); 
		}
		if(uIVideo.getSelectedVideocategory().length == 0 ){
			errors.rejectValue("videocategory", "videocategoryError"); 
		}
		
	}

}
