package com.example.demo.test;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("Two")
public class TestAutowiredTwoServiceImpl implements ITestAutowiredService {

	@Override
	public void sayHello() {
		System.out.println("SayHellTwo");
	}
}
