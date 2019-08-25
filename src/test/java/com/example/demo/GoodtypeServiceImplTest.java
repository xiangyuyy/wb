package com.example.demo;

import static org.junit.Assert.*;

import java.util.Formatter;
import java.util.TimeZone;
import java.util.logging.SimpleFormatter;
import java.text.*;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.balancerecord.service.IBalancerecordService;
import com.example.demo.goodtype.entity.Goodtype;
import com.example.demo.goodtype.service.IGoodtypeService;
import com.example.demo.hopeorder.modelvo.OrderStatusEunm;
import com.example.demo.teacher.entity.Student;
import com.example.demo.teacher.service.IStudentService;
import com.mysql.fabric.xmlrpc.base.Data;

import lombok.experimental.var;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GoodtypeServiceImplTest {
	@Autowired
	private IGoodtypeService goodtyperService;
	@Autowired
	private IStudentService studentService;
	@Autowired
	private IBalancerecordService balancerecordService;
	@Test
	public void testGetPageList() {
		
		SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Date date = new Date();
		String s = isoFormat.format(date);
		System.out.println(s);

		/*		isoFormat.setTimeZone(TimeZone.getTimeZone("utc+8"));*/
		//Date d = isoFormat.parse("1566461285000");
		
/*        String str = "2013-01-21 15:10:20";  
        Date d = isoFormat.parse(str);*/
	    System.out.println("asdsf");
/*		SimpleDateFormat isoFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = isoFormat1.parse(d);
		System.err.println(date.getTime());*/
/*		Goodtype g = goodtyperService.selectById(2);
		System.out.println(g.getName());	
		String string = "";
		int count = balancerecordService.getCount();
		var list = balancerecordService.getTestList();
		list.forEach(x -> {
			System.out.println(x.getAfterbalance());
		});*/
		//studentService.selectsById(1);
	}
/*	@Test
	public void test1GetPageList() {
		Goodtype g = goodtyperService.selectById(2);
		var testEnum = OrderStatusEunm.getByType(new Integer(0));
		var testEnum1 = OrderStatusEunm.getByType(new Integer(100));
		
		
		var time = System.currentTimeMillis();
		var tVar = time / 1000;
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	    String time1 = formatter.format(time);
	    String tVar1 = formatter1.format(tVar);
		System.out.println(time);
		System.out.println(tVar);
		System.out.println(time1);
		System.out.println(tVar1);
		System.out.println(g.getName());
		
	}*/

}


