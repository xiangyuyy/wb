package com.example.demo.exception.meta;

import com.example.demo.exception.define.ExceptionSeverity;
import com.example.demo.exception.define.ISkyBaseExcetion;

public class SkySysEncryEncodeErrException  extends RuntimeException  implements ISkyBaseExcetion {
	
	private static final long serialVersionUID = -4869063389986553535L;

	protected int severity = ExceptionSeverity.ERROR;
	protected String errorMessage = "加密失败!";
	protected int errorCode = 4002;
	
	public SkySysEncryEncodeErrException() {
		
	}
	
	public SkySysEncryEncodeErrException(String message) {
		super(message);
	}
	
	public SkySysEncryEncodeErrException(String message, Throwable cause) {
		super(message, cause);
	}

}
