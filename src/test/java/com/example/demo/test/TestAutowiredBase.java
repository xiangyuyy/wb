package com.example.demo.test;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

public class TestAutowiredBase {
	@Resource
	//@Qualifier("One")
	private ITestAutowiredService testAutowiredService;
	public void sayHello() {
		testAutowiredService.sayHello();
	}
}
