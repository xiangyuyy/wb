package com.example.demo.cache;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import com.example.demo.utils.SpELParser;
import org.apache.commons.collections.CollectionUtils;
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
public class CacheMultipleByMapProvide {
	@Resource
	private CacheUtil cacheUtil;

	@Pointcut("@annotation(com.example.demo.cache.CacheMultipleByMap)")
	public void MultipleCacheByMapPointCut() {
	}

	@SuppressWarnings("unchecked")
	@Around(value = "MultipleCacheByMapPointCut()")
	public List<Object> multipleCacheByMapPointCut(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		List<Object> needFindValues = new ArrayList<>();
		List<Object> resultList = new ArrayList<>();
		List<String> values = null;

		try {
			Method method = ((MethodSignature) proceedingJoinPoint.getSignature()).getMethod();

			CacheMultipleByMap cacheInteferce = method.getAnnotation(CacheMultipleByMap.class);

			SpELParser spELParser = new SpELParser(proceedingJoinPoint);

			String preKey = cacheInteferce.preKey();

			if (StringUtils.isNotEmpty(cacheInteferce.key())) {
				preKey += spELParser.parseExpression(cacheInteferce.key(), String.class);
			}
			List<Object> field = null;

			if (StringUtils.isNotEmpty(cacheInteferce.field())) {
				field = spELParser.parseExpression(cacheInteferce.field(), List.class);
			}

			List<String> newfield = field.stream().map(item -> item.toString()).collect(Collectors.toList());

			Class<?> cls = cacheInteferce.genericityCls();

			values = cacheUtil.hmget(preKey, newfield);

			int i = 0;
			for (String val : values) {
				if (StringUtils.isEmpty(val)) {
					needFindValues.add(field.get(i));
				} else {
					if (val.startsWith("[")) {
						resultList.addAll(JSON.parseArray(val, cls));
					} else {
						resultList.add(JSON.parseObject(val, cls));
					}
				}
				i++;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return (List<Object>) proceedingJoinPoint.proceed();
		}
		
		if (CollectionUtils.isNotEmpty(needFindValues)) {
			Object[] args = { needFindValues };
			List<Object> object = (List<Object>) proceedingJoinPoint.proceed(args);
			resultList.addAll(object);
		}

		needFindValues = null;
		values = null;
		return resultList;

	}

}
