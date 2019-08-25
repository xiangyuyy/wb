package com.example.demo.exception.meta;

import com.example.demo.exception.define.ExceptionSeverity;
import com.example.demo.exception.define.ISkyBaseExcetion;

public class SkySysEncryDecodeErrException  extends RuntimeException  implements ISkyBaseExcetion {
	
	private static final long serialVersionUID = -4869063389986553535L;

	protected int severity = ExceptionSeverity.ERROR;
	protected String errorMessage = "解码失败!";
	protected int errorCode = 4001;
	
	public SkySysEncryDecodeErrException() {
		
	}
	
	public SkySysEncryDecodeErrException(String message) {
		super(message);
	}
	
	public SkySysEncryDecodeErrException(String message, Throwable cause) {
		super(message, cause);
	}

}
