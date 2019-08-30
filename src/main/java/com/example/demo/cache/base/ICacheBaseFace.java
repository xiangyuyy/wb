package com.example.demo.cache.base;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.data.redis.core.ZSetOperations.TypedTuple;

public interface ICacheBaseFace {

	String get(String key);
	
	String getString(String key);

	byte[] get(byte[] key);

	Long incr(String key);

	Long incrby(String key, Long value);

	void set(String key, String value);

	void set(String key, String value, long seconds);

	void setex(String key, long seconds, String value);

	Boolean setnx(String key, String value);

	void set(byte[] key, byte[] value);

	void setex(byte[] key, byte[] value, long seconds);

	Integer append(String key, String value);

	List<String> mget(List<String> keys);

	void mset(Map<String, String> map);

	Boolean expire(String key, long seconds);

	void del(String key);
	
	void del(List<String> keys);

	Boolean exists(String key);
	
	 void batchDel(String key);
	 
	 void set(String key, long value);
	 
	 Boolean zadd(String key, String value, double score);
	 
	 Set<String> zrangeByScore(String key, double min, double max);
	 
	 Long zadd(String key, Set<TypedTuple<String>> tuples);
	 
	 Long zRemove(String key, Object value);
}
