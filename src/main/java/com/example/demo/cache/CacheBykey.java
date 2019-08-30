package com.example.demo.cache;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 使用redis的map进行缓存
 * 
 * @author kjl
 *
 *
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface CacheBykey {

	String[] value() default {};

	String key() default "";

	String keyGenerator() default "";

	String unless() default "";
	
	long expireSecond() default 0L;

	boolean sync() default false;	

}
