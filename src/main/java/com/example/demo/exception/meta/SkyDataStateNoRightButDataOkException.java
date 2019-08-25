package com.example.demo.exception.meta;

import com.example.demo.exception.define.ExceptionSeverity;
import com.example.demo.exception.define.ISkyBaseExcetion;

/**
 * 状态虽然不对但对整体流程没有影响，前端可以放过该错误
 * @author kjl
 * @date 2017年3月22日 下午4:26:46
 */
public class SkyDataStateNoRightButDataOkException extends RuntimeException  implements ISkyBaseExcetion {
	private static final long serialVersionUID = -5083998172347404405L;
	protected int severity = ExceptionSeverity.NORMAL;
	protected int errorCode = 2005;
	
	public SkyDataStateNoRightButDataOkException() {
	}

	public SkyDataStateNoRightButDataOkException(String message) {
		super(message);
	}

	public SkyDataStateNoRightButDataOkException(String message, Throwable cause) {
		super(message, cause);
	}
}
