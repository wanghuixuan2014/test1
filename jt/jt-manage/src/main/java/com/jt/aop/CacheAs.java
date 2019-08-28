package com.jt.aop;

import org.apache.ibatis.binding.MapperMethod.MethodSignature;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.druid.util.StringUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.anno.Cache_Find;
import com.jt.util.ObjectMapperUtil;

import redis.clients.jedis.JedisCluster;

@Aspect
@Component
public class CacheAs {

	@Autowired(required = false)
	private JedisCluster jedis;  
	@Around("@annotation(cacheFind)")
	public Object around(ProceedingJoinPoint joinPoint,Cache_Find cacheFind ) {
		String key=getKey(joinPoint,cacheFind);
		String resultJSON=jedis.get(key);
		Object resultDate=null;
		if(StringUtils.isEmpty(resultJSON)) {
			 try {
				resultDate = joinPoint.proceed();
				String value = ObjectMapperUtil.toJSON(resultDate);
				if(cacheFind.seconds()>0) {
					jedis.setex(key, cacheFind.seconds(), value);
				}else {
					jedis.set(key, value);
				}
			} catch (Throwable e) {
				e.printStackTrace();
				throw new RuntimeException();
			}
			
		}else {
		Class targetClass= 	getType(joinPoint);
			resultDate=ObjectMapperUtil.toObject(resultJSON, targetClass);
		}
		return resultDate;
	}
	private Class getType(ProceedingJoinPoint joinPoint) {
		MethodSignature signature = (MethodSignature)joinPoint.getSignature();
		return  signature.getReturnType();
	}
	private String getKey(ProceedingJoinPoint joinPoint, Cache_Find cacheFind) {
		String key=cacheFind.key();
		if(StringUtils.isEmpty(key)) {
			String methodName = joinPoint.getSignature().getName();
			String className = joinPoint.getSignature().getDeclaringTypeName();
			Object[] args = joinPoint.getArgs();
			String ags1=String.valueOf(args[0]);
			
			return className+"."+methodName+":"+ags1;
		}else {
			return key;
		}
		
	}
}
