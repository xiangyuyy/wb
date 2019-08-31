package com.example.demo.concurrent;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DistributedLock {

	String lockKey() default "";

	boolean ifSync() default true;  // 是否同步
	
	int lockTime() default 2000; // 单位为ms

	int asyncLockFailRetryTime() default 3; //最大尝试次数
}
