package com.example.demo.exception.meta;

import com.example.demo.exception.define.ExceptionSeverity;
import com.example.demo.exception.define.ISkyBaseExcetion;

/**
 * 没有操作权限
 * @author kjl
 * @date 2017年8月1日 下午4:05:22
 *
 */
public class SkyAuthTokenErrException extends RuntimeException  implements ISkyBaseExcetion {
	private static final long serialVersionUID = -4496044795628936604L;
	protected int severity = ExceptionSeverity.LIGHT;
	protected String errorMessage = "不正确的token！";
	protected int errorCode = 1000;
	
	public SkyAuthTokenErrException() {

	}

	public SkyAuthTokenErrException(String message) {
		super(message);
	}
	
	public SkyAuthTokenErrException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
