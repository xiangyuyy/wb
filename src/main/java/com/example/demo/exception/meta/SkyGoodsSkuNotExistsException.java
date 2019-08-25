package com.example.demo.exception.meta;

import com.example.demo.exception.define.ExceptionSeverity;
import com.example.demo.exception.define.ISkyBaseExcetion;

public class SkyGoodsSkuNotExistsException extends RuntimeException  implements ISkyBaseExcetion {
	private static final long serialVersionUID = 1L;
	protected int severity = ExceptionSeverity.NORMAL;
	protected String errorMessage = "数据状态不正确！";
	protected int errorCode = 20001;
	
	public SkyGoodsSkuNotExistsException() {
	}

	public SkyGoodsSkuNotExistsException(String message) {
		super(message);
	}

	public SkyGoodsSkuNotExistsException(String message, Throwable cause) {
		super(message, cause);
	}
}
