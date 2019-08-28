package com.jt.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties.Jedis;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.jt.pojo.User;
import com.jt.service.DubboUserService;
import com.jt.vo.SysResult;

import redis.clients.jedis.JedisCluster;

@Controller
@RequestMapping("/user")
public class UserController2 {
	
	@Autowired
	private JedisCluster jedisCluster;
	
	@Reference(timeout = 3000,check = false)
	private DubboUserService userService;
	
	/**
	 * 实现用户页面跳转
	 * http://www.jt.com/user/register.html
	 * http://www.jt.com/user/login.html
	 */
	@RequestMapping("/{modu}")
	public String longin(@PathVariable String modu) {
		return modu;
	}
	@RequestMapping("/query/{ticket}")
	@ResponseBody
	public JSONPObject query(@PathVariable String ticket,String callback) {
		String json = jedisCluster.get(ticket);
		JSONPObject jsonpObject;
		if(StringUtils.isEmpty(json)) {
			jsonpObject = new JSONPObject(callback, SysResult.fail());
		}else {
			 jsonpObject = new JSONPObject(callback, SysResult.success());
		}
		return jsonpObject;
	}
	@RequestMapping("/doRegister")
	@ResponseBody
	public SysResult inserUser(User user) {
		userService.insertUser(user);
		return SysResult.success();
	}
	@RequestMapping("/doLogin")
	@ResponseBody
	public SysResult doLogin(User user,HttpServletResponse response) {
		String ticket = userService.doLogin(user);
		if(StringUtils.isEmpty(ticket)) {
			return SysResult.fail();
		}
		Cookie cookie = new Cookie("JT_TICKT", ticket);
		cookie.setMaxAge(7*24*3600);
		cookie.setPath("/");
		cookie.setDomain("jt.com");
		response.addCookie(cookie);
		return SysResult.success();
	}
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request,HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		String ticket =null;
		if(cookies.length>0) {
		for (Cookie cookie : cookies) {
			if("JT_TICKET".equals(cookie)) {
				ticket = cookie.getValue();
				break;
			}
		}
	}
	if(!StringUtils.isEmpty(ticket)) {
		jedisCluster.del(ticket);
	}
	Cookie cookie = new Cookie("JT_TICKET", "");
	cookie.setMaxAge(0);
	cookie.setPath("/");
	cookie.setDomain("jt.com");
	response.addCookie(cookie);
	return "redirect:/";
}
	}
