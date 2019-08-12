package com.example.demo.test;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("One")
public class TestAutowiredOneServiceImpl implements ITestAutowiredService {

	@Override
	public void sayHello() {
		System.out.println("SayHellONE");
	}
}
