package com.example.demo.cache;

import java.lang.reflect.Method;
import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import com.alibaba.fastjson.JSON;

public class CacheProvideUtil {

	public static Object getMethodAopResult(String value, ProceedingJoinPoint proceedingJoinPoint, Class<?> genericityCls) {
		Class<?> cls = getMethodAopReturnType(proceedingJoinPoint);
		if (cls == List.class) {
			return JSON.parseArray(value, genericityCls);
		}
		return JSON.parseObject(value, cls);
	}

	/**
	 * 获取返回值类型
	 * 
	 * @author kjl
	 * @date 2017年1月13日
	 * @param pjp
	 * @return
	 * @throws Exception
	 */
	public static Class<?> getMethodAopReturnType(ProceedingJoinPoint proceedingJoinPoint) {
		Method method = ((MethodSignature) proceedingJoinPoint.getSignature()).getMethod();
		return method.getReturnType();
	}
}
