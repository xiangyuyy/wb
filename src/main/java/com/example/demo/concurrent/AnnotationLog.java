package com.example.demo.concurrent;

import java.lang.annotation.*;

@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AnnotationLog {

	String Paras() default "";

	boolean ifLog() default true;

}
