package com.jt.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jt.mapper.UserMapper;
import com.jt.pojo.User;

@SpringBootTest
@RunWith(SpringRunner.class)
public class test01 {

	@Autowired
	private UserMapper userMapper;
	@Test
	public void testFind() {
		List<User> userlist = userMapper.findObject();
		System.out.println(userlist);
		
	}
	@Test
	public void testObject() {
		User user = new User();
		
	user.setId(null).setAge(30).setSex("男").setName("汪会宣");
		userMapper.inserObject(user);
		System.out.println("加入成功");
	}
	@Test
	public void insert() {
		User user = new User();
		user.setId(null).setName("mybatis-puls").setAge(3).setSex("男");
		int rows = userMapper.insert(user);
		System.out.println("已库"+rows+"行");
		
	}

}
