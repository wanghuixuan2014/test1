package com.jt.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@TableName(value="user")
//如果表名与对象的名称一致可以不写
public class User {
	@TableId(type = IdType.AUTO)
	private Integer id;
	@TableField(value="name")
	private String name;
	private Integer age;
	private String sex;
	  
	
	
}
