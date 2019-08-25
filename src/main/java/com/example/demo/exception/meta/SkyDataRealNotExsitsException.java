package com.example.demo.exception.meta;

import com.example.demo.exception.define.ExceptionSeverity;
import com.example.demo.exception.define.ISkyBaseExcetion;

public class SkyDataRealNotExsitsException extends RuntimeException  implements ISkyBaseExcetion {

	private static final long serialVersionUID = -5246413283139725067L;
	protected int severity = ExceptionSeverity.NORMAL;
	protected String errorMessage = "数据不存在!";
	protected int errorCode = 20002;
	
	public SkyDataRealNotExsitsException() {
		
	}
	
	public SkyDataRealNotExsitsException(String message) {
		super(message);
	}
	
	public SkyDataRealNotExsitsException(String message, Throwable cause) {
		super(message, cause);
	}
	
	
	
}
