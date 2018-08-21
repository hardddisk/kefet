package com.kefet.dao;

import com.kefet.model.Recover;

public interface RecoverDAO {
	
     void save(Recover recover);

     void update(Recover recover);

     void delete(Recover recover);

     Recover getByUsersId(Integer usersId);
        
}
