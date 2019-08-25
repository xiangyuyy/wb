package com.example.demo.exception.meta;

import java.io.Serializable;

import com.example.demo.exception.define.ExceptionSeverity;
import com.example.demo.exception.define.ISkyBaseExcetion;

/**
 * 数据类型错误
 * 
 * @author kjl
 * @date 2017年3月28日 下午7:23:21
 */
public class SkyParamTypeErrException extends RuntimeException implements Serializable, ISkyBaseExcetion {

	private static final long serialVersionUID = -5022565068678612286L;
	protected int severity = ExceptionSeverity.NORMAL;
	protected String errorMessage = "参数类型不正确!";
	protected int errorCode = 5007;
	
	public SkyParamTypeErrException() {
	}

	public SkyParamTypeErrException(String message) {
		super(message);
	}

	public SkyParamTypeErrException(String message, Throwable cause) {
		super(message, cause);
	}

}
