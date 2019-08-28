package com.jt.interceptor;
//拦截器
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import com.jt.pojo.User;
import com.jt.util.ObjectMapperUtil;
import com.jt.util.UserThreadLocal;

import redis.clients.jedis.JedisCluster;
 @Component
public class UserInterceptor implements HandlerInterceptor {
   @Autowired
   private JedisCluster  jedisCluster;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		Cookie[] cookies = request.getCookies();
		String ticke=null;
		if(cookies.length>0) {
			for (Cookie cookie : cookies) {
				if("JT_TICKET".equals(cookie.getName())) {
				ticke = cookie.getValue();
					break;
				}
			}
		}
		
		if(!StringUtils.isEmpty(ticke)) {
		String valu = jedisCluster.get(ticke);
		if(!StringUtils.isEmpty(valu)) {
			User user = ObjectMapperUtil.toObject(valu, User.class);
			//request.setAttribute("JT_TICKET", user);
			UserThreadLocal.set(user);
			return true;
		}
		}
		
		response.sendRedirect("/user/login.html");
		return  false;
	}
}
