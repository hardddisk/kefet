package com.kefet.dao;

import java.util.List;

import com.kefet.model.Resume;
import com.kefet.model.Users;

public interface ResumeDAO {
	
	void save(Resume resume);
	
	void update(Resume resume);
	
	void delete(Resume resume);
	
	Resume getResume(Integer id);
	
	List<Resume> getResumeByUser(Users user);

}
