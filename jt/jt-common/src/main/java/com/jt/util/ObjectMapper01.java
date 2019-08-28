package com.jt.util;


import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectMapper01 {

	public static ObjectMapper mapper=new ObjectMapper();
	public static String dojson(Object value) {
		String obj;
		try {
			obj = mapper.writeValueAsString(value);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return obj;
	}

	public static <T> T doObj(String json,Class<T> c) {
		T retult=null;
		try {
			retult = mapper.readValue(json, c);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		
		return retult;
	}
	
}
