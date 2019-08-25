package com.example.demo.exception.meta;

import com.example.demo.exception.define.ExceptionSeverity;
import com.example.demo.exception.define.ISkyBaseExcetion;

/**
 * @author kjl
 * @date 2017年8月1日 下午4:05:22
 *
 */
public class SkyAuthNoRightException extends RuntimeException  implements ISkyBaseExcetion {
	private static final long serialVersionUID = -4496044795628936604L;

	protected int severity = ExceptionSeverity.LIGHT;
	protected String errorMessage = "您缺少操作权限！";
	protected int errorCode = 1003;
	
	public SkyAuthNoRightException() {

	}

	public SkyAuthNoRightException(String message) {
		super(message);
	}
	
	public SkyAuthNoRightException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
