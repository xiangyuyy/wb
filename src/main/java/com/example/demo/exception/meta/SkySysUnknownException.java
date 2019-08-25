package com.example.demo.exception.meta;

import com.example.demo.exception.define.ExceptionSeverity;
import com.example.demo.exception.define.ISkyBaseExcetion;

public class SkySysUnknownException  extends RuntimeException  implements ISkyBaseExcetion {
	
	private static final long serialVersionUID = -4869063389986553535L;

	protected int severity = ExceptionSeverity.ERROR;
	protected String errorMessage = "系统未知异常，请稍后重试!";
	public static int errorCode = 4004;
	public SkySysUnknownException() {
		
	}
	
	public SkySysUnknownException(String message) {
		super(message);
	}
	
	public SkySysUnknownException(String message, Throwable cause) {
		super(message, cause);
	}

}
