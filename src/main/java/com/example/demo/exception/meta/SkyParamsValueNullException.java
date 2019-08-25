package com.example.demo.exception.meta;

import com.example.demo.exception.define.ExceptionSeverity;
import com.example.demo.exception.define.ISkyBaseExcetion;

public class SkyParamsValueNullException extends RuntimeException  implements ISkyBaseExcetion {

	private static final long serialVersionUID = 5705508723885037511L;
	protected int severity = ExceptionSeverity.NORMAL;
	protected String errorMessage = "参数不能为空!";
	protected int errorCode = 5005;
	
	public SkyParamsValueNullException() {
	}

	public SkyParamsValueNullException(String message) {
		super(message);
	}
	
	public SkyParamsValueNullException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
