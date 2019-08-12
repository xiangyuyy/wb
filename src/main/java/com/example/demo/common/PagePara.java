package com.example.demo.common;


import lombok.Data;
import lombok.experimental.Accessors;
/*分页传入后台model*/
@Data
@Accessors(chain = true)
public class PagePara {
	private static final long serialVersionUID = 1L;

	private int page = 1;
	private int limit = 10;
	
}
