package com.example.demo.cache.base;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import com.example.demo.cache.CacheUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations.TypedTuple;
import org.springframework.stereotype.Component;


@Component
public class RedisTemplateCacheBaseFace extends RedisTemplateHashBaseFace implements CacheUtil {

	@Resource
	private RedisTemplate<byte[], byte[]> redisByteTemplate;

	@Override
	public String get(String key) {
		return (String) redisTemplate.opsForValue().get(key);
	}
	
	@Override
	public String getString(String key) {
		return redisStringValueTemplate.opsForValue().get(key);
	}

	public byte[] get(byte[] key) {
		return redisByteTemplate.opsForValue().get(key);
	}

	@Override
	public void setex(byte[] key, byte[] value, long seconds) {
		redisByteTemplate.opsForValue().set(key, value, seconds, TimeUnit.SECONDS);
	}

	@Override
	public void set(byte[] key, byte[] value) {
		redisByteTemplate.opsForValue().set(key, value);
	}

	@Override
	public Long incr(String key) {
		return redisStringValueTemplate.opsForValue().increment(key, 1);
	}

	@Override
	public Long incrby(String key, Long value) {
		return redisStringValueTemplate.opsForValue().increment(key, value);
	}

	@Override
	public void set(String key, String value) {
		redisTemplate.opsForValue().set(key, value);
	}

	@Override
	public void set(String key, long value) {
		redisStringValueTemplate.opsForValue().set(key, String.valueOf(value));
	}

	@Override
	public void set(String key, String value, long seconds) {
		redisTemplate.opsForValue().set(key, value, seconds, TimeUnit.SECONDS);
	}

	@Override
	public Integer append(String key, String value) {
		return redisTemplate.opsForValue().append(key, value);
	}

	@Override
	public List<String> mget(List<String> keys) {
		return redisTemplate.opsForValue().multiGet(keys);
	}

	@Override
	public void mset(Map<String, String> map) {
		redisTemplate.opsForValue().multiSet(map);
	}

	@Override
	public void setex(String key, long seconds, String value) {
		redisTemplate.opsForValue().set(key, value, seconds, TimeUnit.SECONDS);
	}

	@Override
	public Boolean expire(String key, long seconds) {
		return redisTemplate.expire(key, seconds, TimeUnit.SECONDS);
	}

	@Override
	public void del(List<String> keys) {
		redisTemplate.delete(keys);
	}

	@Override
	public Boolean setnx(String key, String value) {
		return redisTemplate.opsForValue().setIfAbsent(key, value);
	}

	@Override
	public Boolean exists(String key) {
		return redisTemplate.hasKey(key);
	}

	@Override
	public void del(String key) {
		redisTemplate.delete(key);
	}

	public void batchDel(String key) {
		Set<String> keys = redisTemplate.keys(key);
		redisTemplate.delete(keys);
	}

	public Boolean zadd(String key, String value, double score) {
		return redisTemplate.opsForZSet().add(key, value, score);
	}

	public Set<String> zrangeByScore(String key, double min, double max) {
		return redisTemplate.opsForZSet().rangeByScore(key, min, max);
	}

	public Long zadd(String key, Set<TypedTuple<String>> tuples) {
		return redisTemplate.opsForZSet().add(key, tuples);
	}

	public Long zRemove(String key, Object value) {
		return redisTemplate.opsForZSet().remove(key, value);
	}

}
