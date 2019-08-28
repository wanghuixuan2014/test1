package com.jt.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.jt.mapper.UserMapper;
import com.jt.pojo.User;

@SpringBootTest
@RunWith(SpringRunner.class)
public class testUserMapper {
    
	@Autowired
	private UserMapper userMapper;
	
	@Test
	public void testSelectOne() {
		int id=56;
		User user=userMapper.selectById(id);
		System.out.println(user);
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("name","mybatis-puls");
	User user2=userMapper.selectOne(queryWrapper);
	System.out.println(user2);
	}
	

	/**
	 * 查询多个数据
	 * =  eq
	 * >  gt
	 * >= ge
	 * <  lt
	 * <= le
	 */
	@Test
	public void testList() {
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		queryWrapper.gt("age", 18).eq("sex", "女");
		
		List<User> user= userMapper.selectList(queryWrapper);
		System.out.println(user);
	}
	
	@Test
	public void testUpdateUser() {
		User user = new User();
		user.setAge(5000).setName("李总").setSex("女").setId(54);
		int rows = userMapper.updateById(user);
		System.out.println(rows);
		User user2 = new User();
		user2.setAge(200).setName("东西");
		UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
		updateWrapper.eq("id", 55);
		
		int rows2 = userMapper.update(user2, updateWrapper);
		System.out.println(rows2);
	}
	@Test
	public void testDelete() {
//		userMapper.deleteById(56);
//		System.out.println("数据已删");
//		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//		queryWrapper.eq("id", 56);
//		userMapper.delete(queryWrapper);
		//delete from sys_role where id in(1,2,3)
		Integer[] intarray= {56,57,58};
		List<Integer> idList=Arrays.asList(intarray);
		
		userMapper.deleteBatchIds(idList);
		System.out.println("数据已删");
	}
}
