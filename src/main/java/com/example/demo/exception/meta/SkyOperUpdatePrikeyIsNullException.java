package com.example.demo.exception.meta;

import com.example.demo.exception.define.ExceptionSeverity;
import com.example.demo.exception.define.ISkyBaseExcetion;

/**
 * 主键为空
 * @author kjl
 * @date 2017年3月3日 下午6:24:23
 */
public class SkyOperUpdatePrikeyIsNullException extends RuntimeException  implements ISkyBaseExcetion {

	private static final long serialVersionUID = -5246413283139725067L;
	protected int severity = ExceptionSeverity.ERROR;
	protected String errorMessage = "主键生成不正确!";
	protected int errorCode = 3004;
	
	public SkyOperUpdatePrikeyIsNullException() {
		
	}
	
	public SkyOperUpdatePrikeyIsNullException(String message) {
		super(message);
	}
	
	public SkyOperUpdatePrikeyIsNullException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
