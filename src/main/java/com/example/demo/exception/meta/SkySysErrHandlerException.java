package com.example.demo.exception.meta;

import com.example.demo.exception.define.ExceptionSeverity;
import com.example.demo.exception.define.ISkyBaseExcetion;

public class SkySysErrHandlerException  extends RuntimeException  implements ISkyBaseExcetion {
	
	private static final long serialVersionUID = -4869063389986553535L;

	protected int severity = ExceptionSeverity.ERROR;
	protected String errorMessage = "系统异常，请稍后重试!";
	protected int errorCode = 4003;
	
	public SkySysErrHandlerException() {
		
	}
	
	public SkySysErrHandlerException(String message) {
		super(message);
	}
	
	public SkySysErrHandlerException(String message, Throwable cause) {
		super(message, cause);
	}

}
