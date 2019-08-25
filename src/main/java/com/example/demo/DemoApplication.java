package com.example.demo;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.LinkedList;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 160707
 *
 */

@MapperScan(basePackages = { "com.example.demo.*.mapper" })
@SpringBootApplication
public class DemoApplication {

	private static final Logger logger = LoggerFactory.getLogger(DemoApplication.class);

	/**
	 * @param args
	 */
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);	
		logger.info("========================启动完毕========================");
	}

}
