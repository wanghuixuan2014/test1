package com.jt.severicImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.jt.mapper.UserMapper;
import com.jt.pojo.User;
import com.jt.severic.UserService;

@Service

public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	@Override
	public List<User> findAll() {
		List<User> list = userMapper.selectList(null);
		return list;
	}

	

}
