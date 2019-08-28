package com.jt.vo;

import java.util.List;

import com.jt.pojo.Item;

import lombok.Data;

@Data
public class EasyUITree {

	
	private Integer total;
   private	List<Item> rows;
}
