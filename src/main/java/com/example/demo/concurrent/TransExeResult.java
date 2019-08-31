package com.example.demo.concurrent;

import lombok.Data;

@Data
public class TransExeResult {

	Integer status; // 0 失败, 1 成功
	Object result;

}
