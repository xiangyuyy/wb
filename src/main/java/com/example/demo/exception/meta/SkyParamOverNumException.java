package com.example.demo.exception.meta;

import com.example.demo.exception.define.ExceptionSeverity;
import com.example.demo.exception.define.ISkyBaseExcetion;

/**
 * 同一个参数多次传入系统异常
 * @author kjl
 * @date 2017年2月22日 下午1:18:34
 */
public class SkyParamOverNumException extends RuntimeException  implements ISkyBaseExcetion {

	private static final long serialVersionUID = 5312085829524176026L;
	protected int severity = ExceptionSeverity.LIGHT;
	protected String errorMessage = "多遍传入同一参数!";
	protected int errorCode = 5002;
	
	public SkyParamOverNumException() {
	}

	public SkyParamOverNumException(String message) {
		super(message);
	}
	
	public SkyParamOverNumException(String message, Throwable cause) {
		super(message, cause);
	}
}
