package com.example.demo.concurrent;

import com.example.demo.cache.RedisLockUtil;
import com.example.demo.utils.SpELParser;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
@Aspect
public class AnnotationLogProvide {
	/**
	 * AOP切点表达式
	 */
	private static final String pointcutExpression = "execution(* com.example.demo.good.web.GoodController.*(..))";

	/**
	 * 定义切点Pointcut
	 */
	@Pointcut(pointcutExpression)
	public void eventPointcut() {
	}

	@Around("eventPointcut()")
	public Object annotationLogProvideAround(ProceedingJoinPoint joinPoint) throws Throwable {
		MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();
		Method method = methodSignature.getMethod();
		AnnotationLog annotation = method.getAnnotation(AnnotationLog.class);
		SpELParser spELParser = new SpELParser(joinPoint);
		String result = spELParser.parseExpression(annotation.Paras(), String.class);
		boolean ifLog = annotation.ifLog();
		if (ifLog) {
			System.out.println("ifLog +\"result\" + result = " + ifLog +"result" + result);
			Object obj = null;
			obj = joinPoint.proceed();
			return obj;
		} else {
			System.out.println("=============");
			Object obj = null;
			obj = joinPoint.proceed();
			return obj;
		}
	}
}
