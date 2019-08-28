package com.jt.advic;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jt.vo.SysResult;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class SysResultControllerAdvice {

	@ExceptionHandler(RuntimeException.class)
	public SysResult sysResultException(Exception e) {
		e.printStackTrace();
		log.error("服务器异常"+e.getMessage());
		return SysResult.fail();
	}
}
