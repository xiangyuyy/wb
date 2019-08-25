package com.example.demo.exception.meta;

import com.example.demo.exception.define.ExceptionSeverity;
import com.example.demo.exception.define.ISkyBaseExcetion;

/**
 * 入参为空异常
 * @author kjl
 * @date 2017年8月1日 下午4:05:38
 *
 */
public class SkyParamNullException extends RuntimeException  implements ISkyBaseExcetion {

	private static final long serialVersionUID = 8802579483147284545L;
	protected int severity = ExceptionSeverity.NORMAL;
	protected String errorMessage = "参数不能为空!";
	protected int errorCode = 5001;
	
	public SkyParamNullException() {
	}

	public SkyParamNullException(String message) {
		super(message);
	}
	
	public SkyParamNullException(String message, Throwable cause) {
		super(message, cause);
	}

}
