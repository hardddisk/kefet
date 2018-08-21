package com.kefet.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.kefet.utility.model.UIImage;

@Component
public class ImageValidator implements Validator {

	@Override
	public boolean supports(Class<?> aClass) {
		// TODO Auto-generated method stub
		return UIImage.class.equals(aClass);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		UIImage uIimage = (UIImage) obj;
		
		
		if(uIimage.getAlbumName().isEmpty() ){
		//	System.out.println("^^^^^^^ImageValidator^^^^^^^");
			errors.rejectValue("albumName", "albumName"); 
		}
	}
}
