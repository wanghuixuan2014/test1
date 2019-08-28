package com.jt.thro;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jt.vo.SysResult;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class SysResult02 {

	@ExceptionHandler(RuntimeException.class)
	public  SysResult sysresultCon(Exception excep) {
		excep.printStackTrace();
		log.error(excep.getMessage());
		return SysResult.fail();
	}
}
