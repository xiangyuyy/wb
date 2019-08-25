package com.example.demo.exception.meta;

import com.example.demo.exception.define.ExceptionSeverity;
import com.example.demo.exception.define.ISkyBaseExcetion;

/**
 * 数据正在被其他人操作
 * @author kjl
 * @date 2017年3月3日 下午6:21:11
 */
public class SkyDataOperatedByOtherException extends RuntimeException  implements ISkyBaseExcetion {
	private static final long serialVersionUID = -5424215179593803635L;
	protected int severity = ExceptionSeverity.NORMAL;
	protected String errorMessage = "操作超时，请您重试！";
	protected int errorCode = 2003;
	
	public	SkyDataOperatedByOtherException() {
	}
	
	public SkyDataOperatedByOtherException(String message) {
		super(message);
	}
	
	public SkyDataOperatedByOtherException(String message, Throwable cause) {
		super(message, cause);
	}

}
