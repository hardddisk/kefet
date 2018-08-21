package com.kefet.validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.kefet.model.ClientAd;


/**
 * this class it the validation class if a user does not enter specific field. error message will be displayed.
 * @author hardddisk
 *
 */

@Component
public class AdValidator implements Validator {

	@Autowired
	private HttpServletRequest request;
	
	
	@Override
	public boolean supports(Class<?> aClass) {
		return ClientAd.class.equals(aClass);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		ClientAd clientAd = (ClientAd) obj;
		
		System.out.println("@@@@@@@@@@@@@@@@@@@clientAd@@@@@@@@@@@@@@"+clientAd.getAdTitle().isEmpty());
		
		
		HttpSession session = request.getSession();
		
		String captchaString=(String) session.getAttribute("captchaString");
		
		System.out.println("@@@@@@@@@@@@@@@@@@@captchaString@@@@@@@@@@@@@@"+captchaString);
		
		captchaString = captchaString.toUpperCase();
		
		
		
		if(clientAd.getAdCategory().isEmpty()){
			System.out.println("@@@@@@@@@@@@@@@@@clientAd.getAdCategory().isEmpty()@@@@@@@@@@@@@@");

			errors.rejectValue("adCategory", "adCategory"); 
		}if(clientAd.getAdTitle().isEmpty()){
			errors.rejectValue("adTitle", "adTitle"); 
		}if(clientAd.getAdDescription().isEmpty()){
			errors.rejectValue("adDescription", "adDescription"); 
		}if(!clientAd.getCaptchaval().toUpperCase().equals(captchaString)){
			errors.rejectValue("captchaval", "captchaval");
		}if(!clientAd.getEmail().isEmpty()){
			errors.rejectValue("userEmail", "userEmail");
		}if(clientAd.iskBusiness()){
			
			if(clientAd.getPrice().isEmpty()){
				errors.rejectValue("price", "price");
			}if(clientAd.getPercentageDiscount().isEmpty()){
				errors.rejectValue("percentageDiscount", "percentageDiscount");
			}if(clientAd.getDiscountedPrice().isEmpty()){
				errors.rejectValue("discountedPrice", "discountedPrice");
			}
		}
	}
}
