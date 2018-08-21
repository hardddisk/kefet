package com.kefet.dao;

import java.util.List;


import com.kefet.model.Users;

public interface UsersJobsDAO {

	List<String> getUsersSelectedJob(Users user);
        
        int deleteUsersJobs(Users user);
}
