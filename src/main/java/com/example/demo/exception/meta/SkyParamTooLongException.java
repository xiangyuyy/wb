package com.example.demo.exception.meta;

import com.example.demo.exception.define.ExceptionSeverity;
import com.example.demo.exception.define.ISkyBaseExcetion;

/**
 * 数据长度太长了
 * 
 * @author kjl
 * @date 2017年3月3日 下午6:21:11
 */
public class SkyParamTooLongException extends RuntimeException  implements ISkyBaseExcetion {
	private static final long serialVersionUID = -5424215179593803635L;
	protected int severity = ExceptionSeverity.NORMAL;
	protected String errorMessage = "参数过长!";
	protected int errorCode = 5006;
	
	public SkyParamTooLongException() {
	}

	public SkyParamTooLongException(String message) {
		super(message);
	}

	public SkyParamTooLongException(String message, Throwable cause) {
		super(message, cause);
	}
	
	
}
