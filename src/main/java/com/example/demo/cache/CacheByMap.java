package com.example.demo.cache;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
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
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CacheByMap {

	String preKey() default "";
	
	String key() default "";
	
	String field() default "";
	
	String value() default "";
	
	boolean ifCacheNull() default false;
	
	Class<?> genericityCls() default String.class;
	

}
