package com.example.demo.cache;

import java.lang.reflect.Method;

import javax.annotation.Resource;

import com.example.demo.utils.SpELParser;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

@Component
@Aspect
public class CacheByMapProvide {

	@Resource
	private CacheUtil cacheUtil;

	@Pointcut("@annotation(com.example.demo.cache.CacheByMap)")
	public void CacheByMapPointCut() {
	}

	@Around(value = "CacheByMapPointCut()")
	public Object cacheByMapAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		try {
			Method method = ((MethodSignature) proceedingJoinPoint.getSignature()).getMethod();

			CacheByMap cacheByMap = method.getAnnotation(CacheByMap.class);

			SpELParser spELParser = new SpELParser(proceedingJoinPoint);

			String preKey = cacheByMap.preKey();
			if (StringUtils.isNotEmpty(cacheByMap.key())) {
				preKey += spELParser.parseExpression(cacheByMap.key(), String.class);
			}
			String field = null;

			if (StringUtils.isNotEmpty(cacheByMap.field())) {
				field = spELParser.parseExpression(cacheByMap.field(), String.class);
			}
			
			if (StringUtils.isNotEmpty(field)) {
				String value = cacheUtil.hget(preKey, field);
				if (StringUtils.isNotEmpty(value)) {
					Object ob = CacheProvideUtil.getMethodAopResult(value, proceedingJoinPoint, cacheByMap.genericityCls());
					return ob;
				}
				if (cacheByMap.ifCacheNull() && (value != null && value.equals(""))) {
					return null;
				}
			}

			Object retObj = proceedingJoinPoint.proceed();

			if (StringUtils.isNotEmpty(field)) {
				if (retObj != null) {
					cacheUtil.hset(preKey, field, JSON.toJSONString(retObj));
				} else if (cacheByMap.ifCacheNull()) {
					cacheUtil.hset(preKey, field, "");
				}
			}
			return retObj;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
	}

}
