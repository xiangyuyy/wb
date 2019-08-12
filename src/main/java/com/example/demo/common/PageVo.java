package com.example.demo.common;

import java.util.List;

import com.baomidou.mybatisplus.plugins.Page;

import lombok.Data;
import lombok.experimental.Accessors;
/*分页列表model*/
@Data
@Accessors(chain = true)
public class PageVo<T> {
	private static final long serialVersionUID = 1L;

	private int code = 0 ;
	private String msg = "成功";
	private long count;
	private List<T> data;
	
	public void changeToPageVo(Page<T> page) {
		this.count = page.getTotal();
		this.data = page.getRecords();
	}
}
