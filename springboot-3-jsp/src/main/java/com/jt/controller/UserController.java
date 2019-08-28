package com.jt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.pojo.User;
import com.jt.severic.UserService;

@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;

	
	@RequestMapping("findAll")

   public String findAll(Model model) {
		List<User> list = userService.findAll();
		model.addAttribute("userList", list);
		return "userList";
	}
	@RequestMapping("/ajaxUserList")
	@ResponseBody
	public List<User> findUser(){
		return userService.findAll();
	}
	
	@RequestMapping("/user/ajax")
	public String index_ajax() {
		
		return "userList-ajax";
	}
}
