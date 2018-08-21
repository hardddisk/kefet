/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kefet.validator;

import com.kefet.utility.model.UIEmployer;
import com.kefet.utility.model.User;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 *
 * @author hardddisk
 */
@Component
public class EmployerValidator  implements Validator {
    
    @Autowired
    private HttpServletRequest request;
    
    private final static String EMAIL_PATTERN = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";


    @Override
    public boolean supports(Class aClass) {
       // TODO Auto-generated method stub
	return UIEmployer.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UIEmployer uiemployer = (UIEmployer) o;
        
 
        /*
         @NotNull
	private String empEmail;
        @NotNull
	private String empPass;
        @NotNull
	private String empCompanyName;
        @NotNull
	private String empPrimaryPhone;
        @NotNull
	private String empContanctName;
        */
        
		
        HttpSession session = request.getSession();

        String captchaString=(String) session.getAttribute("captchaString");

        captchaString = captchaString.toUpperCase();

        if(uiemployer.getEmpContanctName().isEmpty() ){
                errors.rejectValue("empContanctName", "empContanctName"); 
        }if(uiemployer.getEmpPass().isEmpty() || uiemployer.getConfPassword().isEmpty() || !uiemployer.getEmpPass().equals(uiemployer.getConfPassword()) ){
            errors.rejectValue("empPass", "empPass"); 
        }if(uiemployer.getConfPassword().isEmpty()){
            errors.rejectValue("confPassword", "confPassword"); 
        }if(uiemployer.getEmpCompanyName().isEmpty()){
            errors.rejectValue("empCompanyName", "empCompanyName"); 
        }if(uiemployer.getEmpPrimaryPhone().isEmpty()){
            errors.rejectValue("empPrimaryPhone", "empPrimaryPhone"); 
        }if(uiemployer.getEmpEmail().isEmpty() || !Pattern.matches(EMAIL_PATTERN, uiemployer.getEmpEmail())){
            errors.rejectValue("empEmail", "userEmail"); 
        }if(!uiemployer.getCaptchaval().toUpperCase().equals(captchaString)){
            errors.rejectValue("captchaval", "captchaval");
	}
    }
    
}
