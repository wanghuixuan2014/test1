package com.jt.vo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class EasyUIT {
    
	private Long id;
	private String text;
	private String state;
}
