package com.example.demo.cache;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 该注解用于批量缓存的场景
 * @author kjl
 * @date  2018年9月14日
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CacheMultipleByMap {

	String preKey() default "";

	String key() default "";

	String field() default "";

	String value() default "";

	Class<?> genericityCls() default String.class;

}
