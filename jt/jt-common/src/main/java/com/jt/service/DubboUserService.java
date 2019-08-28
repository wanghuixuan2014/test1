package com.jt.service;

import org.springframework.transaction.annotation.Transactional;

import com.jt.pojo.User;

public interface DubboUserService {

	 String doLogin(User user) ;

	void insertUser(User user);
		

	
//	@Transactional
	
}
