package com.example.demo.exception.meta;

import com.example.demo.exception.define.ExceptionSeverity;
import com.example.demo.exception.define.ISkyBaseExcetion;

/**
 * 非法请求
 * @author kjl
 * @date 2017年8月1日 下午4:05:22
 *
 */
public class SkyAuthRequestIllegalException extends RuntimeException  implements ISkyBaseExcetion {
	private static final long serialVersionUID = -4496044795628936604L;
	
	protected int severity = ExceptionSeverity.LIGHT;
	protected String errorMessage = "非法请求！";
	protected int errorCode = 1005;
	
	public SkyAuthRequestIllegalException() {

	}

	public SkyAuthRequestIllegalException(String message) {
		super(message);
	}
	
	public SkyAuthRequestIllegalException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
