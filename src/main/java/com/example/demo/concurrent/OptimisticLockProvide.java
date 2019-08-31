package com.example.demo.concurrent;

import java.util.List;

import javax.annotation.Resource;

import com.example.demo.configuration.SpringContextUtil;
import com.example.demo.exception.meta.SkyOperUpdateErrException;
import com.example.demo.utils.SpELParser;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.Ordered;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;


import lombok.extern.slf4j.Slf4j;

@Component
@Aspect
@Slf4j
public class OptimisticLockProvide implements Ordered{
	
	private TransactionTemplate transactionTemplate;
	
	@Resource
	private RedisTemplate<byte[], byte[]> redisByteTemplate;

	@Override
	public int getOrder() {
		return 99;
	}
	
	@Around("@annotation(optimisticLock)")
	public Object distributedLockBefore(ProceedingJoinPoint joinPoint, OptimisticLock optimisticLock) throws Throwable {
		
		transactionTemplate = SpringContextUtil.getBean("transactionTemplate");
		if(transactionTemplate == null) {
			throw new SkyOperUpdateErrException("");
		}
				
		if (StringUtils.isEmpty(optimisticLock.lockKey())) {
			return null;
		}
		SpELParser spELParser = new SpELParser(joinPoint);
		String key = spELParser.parseExpression(optimisticLock.lockKey(), String.class) + "_OPT_LOCK";
		Integer maxTry = optimisticLock.maxTry();
		Integer i = 0;
		
		while (true) {
			TransExeResult transExeResult = null;
			try {
				transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
				transExeResult = transactionTemplate.execute(new TransactionCallback<TransExeResult>() {
					@Override
					public TransExeResult doInTransaction(TransactionStatus arg0) {
						TransExeResult result = null;

						result = redisByteTemplate.execute(new SessionCallback<TransExeResult>() {
							@Override
							public TransExeResult execute(RedisOperations operations) throws DataAccessException {
								TransExeResult result = new TransExeResult();
								operations.watch(key);
								operations.multi();
								operations.opsForValue().increment(key, 1);
								try {
									Object object = joinPoint.proceed();
									result.setResult(object);
								} catch (Throwable e) {
									e.printStackTrace();
									result.setStatus(-1);
								}
								List rs = operations.exec();
								if (rs != null && rs.size() > 0) {
									result.setStatus(1);
								} else {
									result.setStatus(-2);
								}
								return result;
							}
						});
						
						if(result!= null && result.getStatus()==1) {
							return result;
						}else {
							throw new RuntimeException("版本已修改,更新失败,稍后重试[乐观锁]!");
						}
						
					}
				});

			} catch (Exception e) {
				e.printStackTrace();
				log.error(e.getMessage());
			}
			
			i++;
			if(transExeResult!=null && transExeResult.getStatus() == 1) {
				return transExeResult.result;
			}
			
			if(i>maxTry) {
				log.error(key + "乐观锁重试超出次数");
				throw new SkyOperUpdateErrException("更新繁忙,请重试!");
			}
		}
		
		
		
	}

}
