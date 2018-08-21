package com.kefet.utility.model;


import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.kefet.utility.DropdownDay;
import com.kefet.utility.DropdownYear;

public class User implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// Fields

	@Pattern(regexp="[A-Z][a-z]+( [A-Z][a-z]+)?")
	private String userFirst;
	@Pattern(regexp="[A-Z][a-z]+( [A-Z][a-z]+)?")
	private String userLast;
	@Email @NotEmpty
	private String userEmail;

	@Pattern(regexp="^.{4,16}$")
	private String userPass;
	@Pattern(regexp="^.{4,16}$")
	private String confPassword;
	@Pattern(regexp="m|f")
	private String userGender;
	@Pattern(regexp="^[a-z0-9_-]{3,15}$")
	private String userUname;
	private String birthdayMonth;
	private String birthdayDay;
	private String birthdayYear;
	@NotEmpty
	private String captchaval;

	private DropdownYear year;
	private DropdownDay day;
	
	
	public String getUserFirst() {
		return userFirst;
	}

	public void setUserFirst(String userFirst) {
		this.userFirst = userFirst;
	}

	public String getUserLast() {
		return userLast;
	}

	public void setUserLast(String userLast) {
		this.userLast = userLast;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPass() {
		return userPass;
	}
	
	public String getConfPassword() {
		return confPassword;
	}

	public void setConfPassword(String confPassword) {
		this.confPassword = confPassword;
	}

	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}

	public String getUserGender() {
		return userGender;
	}

	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}
	
	public String getBirthdayMonth() {
		return birthdayMonth;
	}

	public void setBirthdayMonth(String birthdayMonth) {
		this.birthdayMonth = birthdayMonth;
	}

	public String getBirthdayDay() {
		return birthdayDay;
	}

	public void setBirthdayDay(String birthdayDay) {
		this.birthdayDay = birthdayDay;
	}

	public String getBirthdayYear() {
		return birthdayYear;
	}

	public void setBirthdayYear(String birthdayYear) {
		this.birthdayYear = birthdayYear;
	}
	
	
	public String getUserUname() {
		return userUname;
	}

	public void setUserUname(String userUname) {
		this.userUname = userUname;
	}

	public DropdownDay getDay() {
		return day;
	}

	public void setDay(DropdownDay day) {
		this.day = day;
	}

	public DropdownYear getYear() {
		return year;
	}

	public void setYear(DropdownYear year) {
		this.year = year;
	}
	
	public String getCaptchaval() {
		return captchaval;
	}

	public void setCaptchaval(String captchaval) {
		this.captchaval = captchaval;
	}

}
