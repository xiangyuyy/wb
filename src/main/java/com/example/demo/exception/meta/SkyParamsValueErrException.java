package com.example.demo.exception.meta;

import com.example.demo.exception.define.ExceptionSeverity;
import com.example.demo.exception.define.ISkyBaseExcetion;

public class SkyParamsValueErrException extends RuntimeException  implements ISkyBaseExcetion {

	private static final long serialVersionUID = 5705508723885037511L;
	protected int severity = ExceptionSeverity.NORMAL;
	protected String errorMessage = "参数不正确!";
	protected int errorCode = 5004;
	
	public SkyParamsValueErrException() {
	}

	public SkyParamsValueErrException(String message) {
		super(message);
	}
	
	public SkyParamsValueErrException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
