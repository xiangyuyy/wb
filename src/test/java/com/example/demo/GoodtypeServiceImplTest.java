package com.example.demo;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.goodtype.entity.Goodtype;
import com.example.demo.goodtype.service.IGoodtypeService;
import com.example.demo.hopeorder.modelvo.OrderStatusEunm;
import com.example.demo.teacher.entity.Student;
import com.example.demo.teacher.service.IStudentService;

import lombok.experimental.var;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GoodtypeServiceImplTest {
	@Autowired
	private IGoodtypeService goodtyperService;
	@Autowired
	private IStudentService studentService;
	@Test
	public void testGetPageList() {
/*		Goodtype g = goodtyperService.selectById(2);
		System.out.println(g.getName());*/
		//studentService.selectsById(1);
	}
	@Test
	public void test1GetPageList() {
		Goodtype g = goodtyperService.selectById(2);
		
		var testEnum = OrderStatusEunm.getByType(new Integer(0));
		var testEnum1 = OrderStatusEunm.getByType(new Integer(100));
		System.out.println(g.getName());
	}

}


