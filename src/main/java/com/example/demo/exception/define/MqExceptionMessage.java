package com.example.demo.exception.define;

import java.io.Serializable;
import java.time.LocalDateTime;


public class MqExceptionMessage implements Serializable {

	private static final long serialVersionUID = 1L;
	private String serverIp;
	private String serverName;
	private String errorMsg;
	private String exMsg;
	private Integer time;
	private String url;
	private Integer severity;

	public MqExceptionMessage(String serverIp, String serverName, String errorMsg, String exMsg, String url, Integer severity) {
		super();
		this.serverIp = serverIp;
		this.serverName = serverName;
		this.errorMsg = errorMsg;
		this.exMsg = exMsg;
		this.time = (int) (System.currentTimeMillis() / 1000);
		this.url = url;
		this.severity = severity;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getServerIp() {
		return serverIp;
	}

	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public Integer getTime() {
		return time;
	}

	public void setTime(Integer time) {
		this.time = time;
	}

	public String getExMsg() {
		return exMsg;
	}

	public void setExMsg(String exMsg) {
		this.exMsg = exMsg;
	}

	public Integer getSeverity() {
		return severity;
	}

	public void setSeverity(Integer severity) {
		this.severity = severity;
	}
	
}
