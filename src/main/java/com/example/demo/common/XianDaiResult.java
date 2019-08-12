package com.example.demo.common;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class XianDaiResult {
	private static final long serialVersionUID = 1L;

	private boolean success;
	private String message;
	private String data;
}