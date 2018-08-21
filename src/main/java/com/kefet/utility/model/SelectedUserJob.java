package com.kefet.utility.model;

//http://fruzenshtein.com/spring-mvc-form-checkbox/

import org.hibernate.validator.constraints.NotEmpty;


public class SelectedUserJob {
    
        @NotEmpty
	 private String[] selectJob;
	 
	public String[] getSelectJob() {
		return selectJob;
	}

	public void setSelectJob(String[] selectJob) {
		this.selectJob = selectJob;
	}
}
