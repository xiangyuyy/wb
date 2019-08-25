package com.example.demo.exception.meta;

import com.example.demo.exception.define.ExceptionSeverity;
import com.example.demo.exception.define.ISkyBaseExcetion;

/**
 * 参数错误异常
 * @author kjl
 * @date 2017年2月28日 下午2:39:07
 */
public class SkyParamPattenErrException extends RuntimeException  implements ISkyBaseExcetion {
	private static final long serialVersionUID = 4222427650694435452L;
	protected int severity = ExceptionSeverity.NORMAL;
	protected String errorMessage = "参数格式错误!";
	protected int errorCode = 5003;
	
	public SkyParamPattenErrException() {
	}

	public SkyParamPattenErrException(String message) {
		super(message);
	}
	
	public SkyParamPattenErrException(String message, Throwable cause) {
		super(message, cause);
	}
}
