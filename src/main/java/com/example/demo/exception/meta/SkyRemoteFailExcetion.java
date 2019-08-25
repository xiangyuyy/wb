package com.example.demo.exception.meta;

import com.example.demo.exception.define.ExceptionSeverity;
import com.example.demo.exception.define.ISkyBaseExcetion;

/**
 * 远程调用失败
 * 
 * @author kjl
 * @date 2018年9月4日
 */
public class SkyRemoteFailExcetion extends RuntimeException implements ISkyBaseExcetion {

	private static final long serialVersionUID = 2554053298519527396L;

	protected int severity = ExceptionSeverity.ERROR;
	protected String errorMessage = "系统繁忙，请稍后重试";
	protected int errorCode = 4006;

	public SkyRemoteFailExcetion() {

	}

	public SkyRemoteFailExcetion(String message) {
		super(message);
	}

	public SkyRemoteFailExcetion(String message, Throwable cause) {
		super(message, cause);
	}

	public SkyRemoteFailExcetion(String message, Throwable cause, int severity) {
		super(message, cause);
		this.severity = severity;
	}
	
	public SkyRemoteFailExcetion(String message, int severity) {
		super(message);
		this.severity = severity;
	}


}
