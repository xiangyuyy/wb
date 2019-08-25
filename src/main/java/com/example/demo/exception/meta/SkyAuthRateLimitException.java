package com.example.demo.exception.meta;

import com.example.demo.exception.define.ExceptionSeverity;
import com.example.demo.exception.define.ISkyBaseExcetion;

/**
 * 没有操作权限
 * @author kjl
 * @date 2017年8月1日 下午4:05:22
 *
 */
public class SkyAuthRateLimitException extends RuntimeException  implements ISkyBaseExcetion {
	private static final long serialVersionUID = -4496044795628936604L;
	
	protected int severity = ExceptionSeverity.NORMAL;
	protected String errorMessage = "您的手速有点快，稍微等下下哦！";
	protected int errorCode = 1004;
	
	public SkyAuthRateLimitException() {

	}

	public SkyAuthRateLimitException(String message) {
		super(message);
	}
	
	public SkyAuthRateLimitException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
