package com.kefet.utility;

public class DropdownYear {

	private String year;
	private String value;
	
	public DropdownYear(String value, String year){
		this.value = value;
		this.year = year;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}
	
}
