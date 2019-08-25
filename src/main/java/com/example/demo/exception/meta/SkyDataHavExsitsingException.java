package com.example.demo.exception.meta;

import com.example.demo.exception.define.ExceptionSeverity;
import com.example.demo.exception.define.ISkyBaseExcetion;

/**
 * 数据已存在
 * @author kjl
 * @date 2017年3月3日 下午6:24:23
 */
public class SkyDataHavExsitsingException extends RuntimeException  implements ISkyBaseExcetion {

	private static final long serialVersionUID = -5246413283139725067L;
	protected int severity = ExceptionSeverity.NORMAL;
	protected String errorMessage = "数据已存在！";
	protected int errorCode = 2001;
	
	public SkyDataHavExsitsingException() {
		
	}
	
	public SkyDataHavExsitsingException(String message) {
		super(message);
	}
	
	public SkyDataHavExsitsingException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
