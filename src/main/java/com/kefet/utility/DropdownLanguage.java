package com.kefet.utility;

public class DropdownLanguage {
	
	private String language;
	private String value;
	
	public DropdownLanguage(String value, String language){
		this.value = value;
		this.language = language;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
}
