package com.example.demo.concurrent;

import javax.annotation.Resource;

import com.example.demo.cache.RedisLockUtil;
import com.example.demo.utils.SpELParser;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class RedisLockProvide {

	@Resource
	private RedisLockUtil redisLockUtil;

	@Around("@annotation(distributedLock)")
	public Object distributedLockAround(ProceedingJoinPoint joinPoint, DistributedLock distributedLock) throws Throwable {
		if (StringUtils.isEmpty(distributedLock.lockKey())) {
			return null;
		}
		SpELParser spELParser = new SpELParser(joinPoint);
		String key = spELParser.parseExpression(distributedLock.lockKey(), String.class) + "_LOCK";
		boolean ifSycn = distributedLock.ifSync();
		Object obj = null;
		if (ifSycn) {
			redisLockUtil.syncLock(key, distributedLock.lockTime());
			try {
				obj = joinPoint.proceed();
			} catch (Exception ex) {
				redisLockUtil.unlock(key);
				throw ex;
			}
			redisLockUtil.unlock(key);
		} else {
			redisLockUtil.asyncLock(key, joinPoint, obj, distributedLock.asyncLockFailRetryTime());
		}
		return obj;
	}
}
