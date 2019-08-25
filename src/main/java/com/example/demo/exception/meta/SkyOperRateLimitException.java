package com.example.demo.exception.meta;

import com.example.demo.exception.define.ExceptionSeverity;
import com.example.demo.exception.define.ISkyBaseExcetion;

public class SkyOperRateLimitException  extends RuntimeException  implements ISkyBaseExcetion {
	
	private static final long serialVersionUID = -4869063389986553535L;

	protected int severity = ExceptionSeverity.ERROR;
	protected String errorMessage = "活动太火爆了，请稍后再试一下呢!";
	protected int errorCode = 3005;
	
	public SkyOperRateLimitException() {
		
	}
	
	public SkyOperRateLimitException(String message) {
		super(message);
	}
	
	public SkyOperRateLimitException(String message, Throwable cause) {
		super(message, cause);
	}

}
