package com.test;

import java.util.Arrays;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

public class AppTest {

	@Test
	public void logintest() {
		
		Factory<SecurityManager> factory=new IniSecurityManagerFactory("classpath:config/shrio.ini");
		SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		Subject subject = SecurityUtils.getSubject();
		 UsernamePasswordToken token = new UsernamePasswordToken("admin2","admin1");
		 System.out.println(token);
		try {
			 subject.login(token);
			 System.out.println("成功");
		} catch (Exception e) {
		System.out.println("失败");
		}
		
		boolean authenticated=subject.isAuthenticated();
		System.out.println(authenticated);
		subject.logout();
	}
	@Test
	public void testRealm() {
		Factory<SecurityManager> factory=new IniSecurityManagerFactory("classpath:config/shrio2.ini");
		SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		Subject subject = SecurityUtils.getSubject();
		 UsernamePasswordToken token = new UsernamePasswordToken("admin2","admin1");
		 System.out.println(token);
		try {
			 subject.login(token);
			 System.out.println("成功");
		} catch (Exception e) {
		System.out.println("失败");
		}
		
		boolean authenticated=subject.isAuthenticated();
		System.out.println(authenticated);
		subject.logout();
	}
	@Test
	public void testpate() {
		Factory<SecurityManager> factory=new IniSecurityManagerFactory("classpath:config/shrio3.ini");
		SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		Subject subject = SecurityUtils.getSubject();
		 UsernamePasswordToken token = new UsernamePasswordToken("admin","admin");
		 System.out.println(token);
		try {
			 subject.login(token);
			 System.out.println("成功");
		} catch (Exception e) {
		System.out.println("失败");
		}
		boolean hasRole = subject.hasRole("role3");
		System.out.println(hasRole);
		boolean permitted = subject.isPermitted("delete");
		System.out.println(permitted);
		boolean hasAllRoles = subject.hasAllRoles(Arrays.asList("role1","role2"));
		System.out.println(hasAllRoles);
	}
}
