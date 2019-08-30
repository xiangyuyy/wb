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


@Component
@Aspect
public class CacheEvictByMapProvide {

	@Resource
	private CacheUtil cacheUtil;

	@Pointcut("@annotation(com.example.demo.cache.CacheEvictByMap)")
	public void CacheEvictByMapPointCut() {
	}

	@Around(value = "CacheEvictByMapPointCut()")
	public Object cacheEvictByMapBefore(ProceedingJoinPoint joinPoint) throws Throwable {
		Object result = joinPoint.proceed();
		Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
		CacheEvictByMap anno = method.getAnnotation(CacheEvictByMap.class);
		SpELParser spELParser = new SpELParser(joinPoint, result);

		String key = anno.preKey();
		if (StringUtils.isNotEmpty(anno.key())) {
			key += spELParser.parseExpression(anno.key(), String.class);
		}

		String field = null;
		if (StringUtils.isNotEmpty(anno.field())) {
			if (anno.field().startsWith("#result") && result == null) {
				return result;
			}
			field = spELParser.parseExpression(anno.field(), String.class);
		}
		if (anno != null) {
			if (anno.evictByKey()) {
				cacheUtil.del(key);
			} else {
				if (StringUtils.isNotEmpty(anno.field())) {
					cacheUtil.hdel(key, field);
				}
			}
		}
		return result;
	}

}
