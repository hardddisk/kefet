package com.kefet.utility;

public class DropdownDay {

	private String day;
	private String value;
	
	public DropdownDay(String value, String day){
		this.value = value;
		this.day = day;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}
	
}
