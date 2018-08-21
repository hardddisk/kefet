package com.kefet.dao;


import com.kefet.model.Facebookdata;




public interface FacebookdataDAO {
	
	void save(Facebookdata facebookdata);
	
	void update(Facebookdata facebookdata);
	
	Facebookdata getFacebookdata(long facebookUid);
	
}
