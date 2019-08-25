package com.example.demo;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.experimental.var;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Test
	public void contextLoads() {
		System.out.println("12122");
		String zString = ZoneId.SHORT_IDS.get("CTT");
		ZoneId zoneId = ZoneId.of(zString);
		
		String zString1 = ZoneId.SHORT_IDS.get("CST");
		ZoneId zoneId1 = ZoneId.of(zString1);
				
		LocalDateTime time = LocalDateTime.now(zoneId);
		LocalDateTime time1 = LocalDateTime.now(zoneId1);
		
		
		//获取秒数
		Long second = time.toEpochSecond(ZoneOffset.of("+8"));
		//获取毫秒数
		Long milliSecond = time.toInstant(ZoneOffset.of("+8")).toEpochMilli();
		System.out.println(time.toString());
		
		System.out.println(time1.toString());
		//测试
	}

}

