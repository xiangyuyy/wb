package com.example.demo.exception.meta;

import com.example.demo.exception.define.ExceptionSeverity;
import com.example.demo.exception.define.ISkyBaseExcetion;

public class SkyPrimaryKeyCreateErrException  extends RuntimeException  implements ISkyBaseExcetion {
	
	private static final long serialVersionUID = -4869063389986553535L;

	protected int severity = ExceptionSeverity.ERROR;
	protected String errorMessage = "主键生成失败!";
	protected int errorCode = 5008;
	
	public SkyPrimaryKeyCreateErrException() {
		
	}
	
	public SkyPrimaryKeyCreateErrException(String message) {
		super(message);
	}
	
	public SkyPrimaryKeyCreateErrException(String message, Throwable cause) {
		super(message, cause);
	}

}
