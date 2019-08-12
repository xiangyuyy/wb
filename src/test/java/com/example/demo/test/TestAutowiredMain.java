package com.example.demo.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.goodtype.entity.Goodtype;
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestAutowiredMain {
	
	@Resource
/*	@Autowired*/
	@Qualifier("Two")
	private ITestAutowiredService testAutowiredService; 
	@Test
	public void test() {
		testAutowiredService.sayHello();
	}
}
