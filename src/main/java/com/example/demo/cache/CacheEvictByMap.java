package com.example.demo.cache;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CacheEvictByMap {

	String preKey() default "";
	
	/**
	 * 根据map中的key删除
	 * @author kjl
	 * @date 2017年3月25日 下午5:28:32
	 * @return
	 */
	String key() default "";
	
	
	String field() default "";

	/**
	 * 是根据key来删除还是 field来删除  true-根据key删除
	 * @author kjl
	 * @date 2017年3月25日 下午5:38:42
	 * @return
	 */
	boolean evictByKey() default true;
	
}
