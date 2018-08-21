package com.kefet.validator;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.kefet.model.Users;
import com.kefet.service.UsersService;
import com.kefet.utility.model.User;

@Component
public class UserValidator implements Validator {
	
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private UsersService usersService;
	
	private final static String EMAIL_PATTERN = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
	
	private final static String INVALID_PATTERN = ".*(>|<|/>).*";
	
	
	@Override
	public boolean supports(Class aClass) {
		// TODO Auto-generated method stub
		return User.class.equals(aClass);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		User user = (User) obj;
		
		HttpSession session = request.getSession();
		
		String captchaString=(String) session.getAttribute("captchaString");
		
		captchaString = captchaString.toUpperCase();
		
		if(user.getUserUname().isEmpty() || user.getUserUname().length() < 4 || user.getUserUname().matches(INVALID_PATTERN) ){
			errors.rejectValue("userUname", "userUname"); 
		}else{
			Users userExist=usersService.getUserByUname(user.getUserUname());
			if(userExist!=null){
				errors.rejectValue("userUname", "userNameExist");
			}
		}if(!Pattern.matches("^[\\p{L} .'-]+$", user.getUserFirst()) || user.getUserFirst().matches(INVALID_PATTERN) ){
			errors.rejectValue("userFirst", "userFirst");
		}if(!Pattern.matches("^[\\p{L} .'-]+$", user.getUserLast())|| user.getUserLast().matches(INVALID_PATTERN)){
			errors.rejectValue("userLast", "userLast");
		}if(!Pattern.matches(EMAIL_PATTERN, user.getUserEmail())){
			errors.rejectValue("userEmail", "userEmail");
		}else{
			Users userExist=usersService.getUserByEmail(user.getUserEmail());
			if(userExist!=null){
				errors.rejectValue("userEmail", "usEmailExist");
			}
		}if(!Pattern.matches("^.{4,16}$", user.getUserPass())|| user.getUserPass().matches(INVALID_PATTERN)){
			errors.rejectValue("userPass", "userPass");
		}if(user.getBirthdayDay().equals("0") || user.getBirthdayMonth().equals("0")|| user.getBirthdayYear().equals("0")){
			errors.rejectValue("birthdayYear", "birthdayYear");
		}if(!Pattern.matches("^.{4,16}$", user.getConfPassword())|| user.getConfPassword().matches(INVALID_PATTERN)){
			errors.rejectValue("confPassword", "confPassword");
		}if(!user.getUserPass().equals(user.getConfPassword())){
			errors.rejectValue("confPassword", "usPasswordNotMatch");
		}if(!user.getUserGender().matches("m|f")){
			errors.rejectValue("userGender", "userGender");
		}if(!user.getCaptchaval().toUpperCase().equals(captchaString)){
			errors.rejectValue("captchaval", "captchaval");
		}if(!isAdgeAboveLimited(user.getBirthdayYear(), user.getBirthdayMonth(), user.getBirthdayDay())){
			errors.rejectValue("birthdayYear", "usBirthdateToYoung");
	    }
	}
	

	
	public boolean isAdgeAboveLimited(String year, String month, String dayOfMonth){
		
		Calendar cal = new GregorianCalendar(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(dayOfMonth));
	    Calendar now = new GregorianCalendar();
	    int res = now.get(Calendar.YEAR) - cal.get(Calendar.YEAR);
	    if ((cal.get(Calendar.MONTH) > now.get(Calendar.MONTH))
	        || (cal.get(Calendar.MONTH) == now.get(Calendar.MONTH) && cal.get(Calendar.DAY_OF_MONTH) > now
	            .get(Calendar.DAY_OF_MONTH))) {
	      res--;
	    }
		
	    if(res >= 14){
		return true;
	    }else{
	    	return false;
	    }
	}
}
