package com.kefet.validator;


import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.kefet.model.Users;
import com.kefet.utility.SecurePassword;
import com.kefet.service.UsersService;
import com.kefet.utility.model.User;


@Component
public class LoginValidator  implements Validator {
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private UsersService usersService;
	
	private final static String EMAIL_PATTERN = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";

	@Override
	public boolean supports(Class aClass) {
		// TODO Auto-generated method stub
		return User.class.equals(aClass);
	}


	@Override
	public void validate(Object obj, Errors errors) {
		
		User user = (User) obj;
		Users userFromDb;
		
		if(!Pattern.matches(EMAIL_PATTERN, user.getUserEmail())){
			errors.rejectValue("userEmail", "userEmail");
		}else{
			 userFromDb=usersService.getUserByEmail(user.getUserEmail());
		 	
			// if the user does not exist show does not exist message.
			if(userFromDb == null){
				errors.rejectValue("userEmail", "unableToFindUser");
			}else{ // if the user exist make sure the password entered matches the one from the database.
				if(!user.getUserEmail().equals(userFromDb.getUserEmail()) || !new String(new com.kefet.utility.SecurePassword().encodeBase64(user.getUserPass())).equals(userFromDb.getUserPass())){
					errors.rejectValue("userEmail", "loginError");
				}else{
				 	// there is no any issue now we can save the user into the session.
					HttpSession session = request.getSession();
				 	setUserPermission(userFromDb, session);
					session.setAttribute("loggedUser", userFromDb);
				}
			}
		}
	}
        
        public void setUserPermission(Users user, HttpSession session){
            if(!user.getUsersAdminroleses().isEmpty()){
                String code=user.getUsersAdminroleses().iterator().next().getAdminroles().getRoleCode();
                session.setAttribute("permission", code);
            }
        }
}
