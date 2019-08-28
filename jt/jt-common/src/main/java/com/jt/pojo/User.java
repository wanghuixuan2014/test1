package com.jt.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.experimental.Accessors;

@TableName("tb_user")
@Data
@Accessors(chain = true)
public class User extends BasePojo{

	@TableId(type = IdType.AUTO)
	private Long id;
	private String username;		//用户名
	private String password;		//密码
	private String phone;			//电话
	private String email;		
}
