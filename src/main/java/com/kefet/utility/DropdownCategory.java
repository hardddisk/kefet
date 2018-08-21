package com.kefet.utility;

public class DropdownCategory {
	
	private String category;
	private String value;
	
	public DropdownCategory(String value, String category){
		this.value = value;
		this.category = category;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
