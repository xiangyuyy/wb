package com.example.demo.exception.meta;

import com.example.demo.exception.define.ExceptionSeverity;
import com.example.demo.exception.define.ISkyBaseExcetion;

/**
 * @author kjl
 * @date 2017年8月1日 下午4:05:22
 */
public class SkyAuthLoginFailException extends RuntimeException  implements ISkyBaseExcetion {
	private static final long serialVersionUID = -4496044795628936604L;

	protected int severity = ExceptionSeverity.LIGHT;
	
	protected String errorMessage = "登录失败，请重试！";
	
	protected int errorCode = 1001;
	
	public SkyAuthLoginFailException() {

	}

	public SkyAuthLoginFailException(String message) {
		super(message);
	}
	
	public SkyAuthLoginFailException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
