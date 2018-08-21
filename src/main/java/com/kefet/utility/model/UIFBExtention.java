package com.kefet.utility.model;

import com.kefet.model.Resume;
import javax.validation.Valid;






public class UIFBExtention {
	
	@Valid
	private Resume resume;
        
        @Valid
	private SelectedUserJob jobs;
	
	
	public UIFBExtention(){
		 this.resume = new Resume();
	//	this.article = new UIArticle();
		this.jobs = new SelectedUserJob();
	}
	public Resume getResume() {
		return resume;
	}
	public void setResume(Resume resume) {
		this.resume = resume;
	}

	
	public SelectedUserJob getJobs() {
		return jobs;
	}

	public void setJobs(SelectedUserJob jobs) {
		this.jobs = jobs;
	}

}
