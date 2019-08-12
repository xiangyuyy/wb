package com.example.demo.common;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class DingdianResult {
	private static final long serialVersionUID = 1L;

	private String code;
	private String msg;
	private String data;
}