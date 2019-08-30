package com.example.demo.cache;

import java.lang.reflect.Method;

import javax.annotation.Resource;

import com.example.demo.exception.meta.SkySysErrHandlerException;
import com.example.demo.utils.SpELParser;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.stereotype.Component;


@Component
@Aspect
public class CacheByKeyProvide {

	@Resource
	private CacheUtil cacheUtil;
	@Resource
	private JdkSerializationRedisSerializer jdkSerializationRedisSerializer;

	@Pointcut("@annotation(com.example.demo.cache.CacheBykey)")
	public void CacheByKeyPointCut() {
	}

	@Around(value = "CacheByKeyPointCut()")
	public Object cacheBykeyAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		try {
			Method method = ((MethodSignature) proceedingJoinPoint.getSignature()).getMethod();

			CacheBykey cacheBykey = method.getAnnotation(CacheBykey.class);

			SpELParser spELParser = new SpELParser(proceedingJoinPoint);

		//	String[] cacheSetVal = cacheBykey.value();

			if (StringUtils.isEmpty(cacheBykey.key())) {
				throw new SkySysErrHandlerException("cache key can't null");
			}
			String key = spELParser.parseExpression(cacheBykey.key(), String.class);

			byte[] value = cacheUtil.get(key.getBytes());

			if (value != null && value.length > 0) {
				return jdkSerializationRedisSerializer.deserialize(value);
			}
			Object retObj = proceedingJoinPoint.proceed();

			if (cacheBykey.expireSecond() > 0) {
				cacheUtil.setex(key.getBytes(), jdkSerializationRedisSerializer.serialize(retObj), cacheBykey.expireSecond());
			} else {
				cacheUtil.set(key.getBytes(), jdkSerializationRedisSerializer.serialize(retObj));
			}

			return retObj;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
}
