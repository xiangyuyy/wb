package com.example.demo.exception.meta;

import com.example.demo.exception.define.ExceptionSeverity;
import com.example.demo.exception.define.ISkyBaseExcetion;

/**
 * 更新失败
 * @author kjl
 * @date 2017年3月3日 下午6:24:23
 */
public class SkyOperDeleteErrException extends RuntimeException  implements ISkyBaseExcetion {

	private static final long serialVersionUID = -5246413283139725067L;
	protected int severity = ExceptionSeverity.ERROR;
	protected String errorMessage = "操作失败！";
	protected int errorCode = 3001;
	
	public SkyOperDeleteErrException() {
		
	}
	
	public SkyOperDeleteErrException(String message) {
		super(message);
	}
	
	public SkyOperDeleteErrException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
