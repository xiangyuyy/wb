package com.example.demo.exception.meta;

import com.example.demo.exception.define.ExceptionSeverity;
import com.example.demo.exception.define.ISkyBaseExcetion;

public class SkyDataStateErrException extends RuntimeException  implements ISkyBaseExcetion {
	private static final long serialVersionUID = -5083998172347404405L;
	protected int severity = ExceptionSeverity.NORMAL;
	protected String errorMessage = "数据状态不正确！";
	protected int errorCode = 2004;
	
	public SkyDataStateErrException() {
	}

	public SkyDataStateErrException(String message) {
		super(message);
	}

	public SkyDataStateErrException(String message, Throwable cause) {
		super(message, cause);
	}
}
