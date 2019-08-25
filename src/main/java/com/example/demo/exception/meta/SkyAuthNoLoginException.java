package com.example.demo.exception.meta;

import com.example.demo.exception.define.ExceptionSeverity;
import com.example.demo.exception.define.ISkyBaseExcetion;

/**
 * @author kjl
 * @date 2017年8月1日 下午4:05:22
 *
 */
public class SkyAuthNoLoginException extends RuntimeException  implements ISkyBaseExcetion {

	private static final long serialVersionUID = 4928774874056430035L;
	
	protected int severity = ExceptionSeverity.LIGHT;
	
	protected String errorMessage = "您还没有登录！";
	
	protected int errorCode = 401;
	
	public SkyAuthNoLoginException() {

	}

	public SkyAuthNoLoginException(String message) {
		super(message);
	}
	
	public SkyAuthNoLoginException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
