package com.example.demo.cache;

import javax.annotation.Resource;

import com.example.demo.exception.meta.SkyDataOperatedByOtherException;
import com.example.demo.exception.meta.SkySysErrHandlerException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import lombok.extern.slf4j.Slf4j;

/**
 * 使用redis作为分布式锁, 项目中暂时使用悲观锁即可
 * 
 * @author kjl
 * @date 2017年2月28日 上午11:29:49
 */
@Slf4j
@Component
public class RedisLockUtil {

	@Resource
	private CacheUtil cacheUtil;
	@Autowired
	private RedisLockUtil redisLockUtil;

	public static final long DEFAULT_TIME_OUT = 2000; // 默认超时时间（毫秒） 默认2秒
	public static final int EXPIRE = 10; // 锁的超时时间（秒），过期删除 最多锁定10秒，过期删除
	public static final String LOCKED = "TRUE"; // 加锁标志
	private static Logger logger = LoggerFactory.getLogger(RedisLockUtil.class);

	public void syncLock(String lockKey, Integer lockTime) {
		if (lockTime != null && lockTime > 0) {
			syncLock(lockTime, lockKey);
		} else {
			syncLock(DEFAULT_TIME_OUT, lockKey);
		}
	}

	public boolean syncLock(long timeout, String lockKey) {
		boolean ifLock = false;
		try {
			ifLock = lock(timeout, lockKey);
		} catch (Exception e) {
			logger.error("redis连接获取异常导致加锁失败,键值:" + lockKey, e);
		}
		if (!ifLock) {
			throw new SkyDataOperatedByOtherException();
		}
		return true;
	}

	public boolean lock(long timeout, String key) {
		long millTime = System.currentTimeMillis();
		try {
			while ((System.currentTimeMillis() - millTime) < timeout) {
				if (cacheUtil.setnx(key, LOCKED)) {
					cacheUtil.expire(key, EXPIRE);
					return true;
				}
				Thread.sleep(100); // 短暂休眠
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("redis添加乐观锁失败, 锁键值:" + key, e);
		}
		return false;
	}

	// 无论是否加锁成功，必须调用
	public void unlock(String key) {
		try {
			cacheUtil.del(key);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("redis解锁失败,键值:" + key, e);
		}
	}

	/**
	 * 存在一个非常严重的问题，就是需要手动提交，这样的话 整个事务执行失败 这里就不会回滚了
	 * 
	 * @author kjl
	 * @date 2019年1月3日
	 * @param key
	 * @param joinPoint
	 * @param obj
	 * @param asyncLockFailRetryTime
	 * @param havExecuteTime
	 * @throws Throwable
	 */
	@Deprecated
	public void handlerForAsyncLock(String key, ProceedingJoinPoint joinPoint, Object obj, int asyncLockFailRetryTime, int havExecuteTime) throws Throwable {
		/*
		 * DefaultTransactionDefinition transactionDefinition = new
		 * DefaultTransactionDefinition();
		 * transactionDefinition.setPropagationBehavior(TransactionDefinition.
		 * PROPAGATION_REQUIRES_NEW); TransactionStatus transactionStatus =
		 * txManager.getTransaction(transactionDefinition); Long lockValue =
		 * cacheUtil.incr(key); obj = joinPoint.proceed(); String lockValueEnd =
		 * cacheUtil.getString(key); if (lockValueEnd != null &&
		 * lockValue.toString().equals(lockValueEnd)) {
		 * log.info("这里为什么不会自动提交事务, 哇靠 这是什么情况"); txManager.commit(transactionStatus); }
		 * else if (havExecuteTime > asyncLockFailRetryTime) {
		 * txManager.rollback(transactionStatus); throw new
		 * SkySysErrHandlerException("更新失败，请稍后重试"); } else {
		 * txManager.rollback(transactionStatus); handlerForAsyncLock(key, joinPoint,
		 * obj, asyncLockFailRetryTime, havExecuteTime + 1); }
		 */
	}

	public void asyncLock(String key, ProceedingJoinPoint joinPoint, Object obj, int asyncLockFailRetryTime) throws Throwable {
		if (asyncLockFailRetryTime == 0)
			asyncLockFailRetryTime = 20;
		for (int i = 1; i < asyncLockFailRetryTime + 2; i++) {
			try {
				boolean ifOk = redisLockUtil.excuteForAsyncLock(key, joinPoint, obj, asyncLockFailRetryTime);
				if (ifOk)
					break;
			} catch (SkyDataOperatedByOtherException ex) {
				if (i > asyncLockFailRetryTime) {
					log.info("乐观锁加锁失败，重试次数:{}", (i - 1));
					throw new SkySysErrHandlerException("更新失败，请稍后重试");
				}
			}
		}
	}

	/**
	 * 使用的是嵌套的子事务
	 * 
	 * @author kjl
	 * @date 2019年1月3日
	 * @param key
	 * @param joinPoint
	 * @param obj
	 * @param asyncLockFailRetryTime
	 * @param havExecuteTime
	 * @throws Throwable
	 */
	@Transactional(propagation = Propagation.NESTED)
	public boolean excuteForAsyncLock(String key, ProceedingJoinPoint joinPoint, Object obj, int asyncLockFailRetryTime) throws Throwable {
		Long lockValue = cacheUtil.incr(key);
		obj = joinPoint.proceed();
		String lockValueEnd = cacheUtil.getString(key);
		if (lockValueEnd != null && lockValue.toString().equals(lockValueEnd)) {
		} else {
			throw new SkyDataOperatedByOtherException();
		}
		return true;
	}

}
