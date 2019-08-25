package com.example.demo.exception.meta;

import com.example.demo.exception.define.ExceptionSeverity;
import com.example.demo.exception.define.ISkyBaseExcetion;

public class SkyDataNotExsitsException extends RuntimeException  implements ISkyBaseExcetion {

	private static final long serialVersionUID = -5246413283139725067L;
	protected int severity = ExceptionSeverity.NORMAL;
	protected String errorMessage = "数据不存在!";
	protected int errorCode = 2002;
	
	public SkyDataNotExsitsException() {
		
	}
	
	public SkyDataNotExsitsException(String message) {
		super(message);
	}
	
	public SkyDataNotExsitsException(String message, Throwable cause) {
		super(message, cause);
	}
	
	
	
}
