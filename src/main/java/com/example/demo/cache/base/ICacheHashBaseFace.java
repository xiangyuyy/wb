package com.example.demo.cache.base;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ICacheHashBaseFace {
	Long hdel(String key, String fields);

	Long hdel(String key, List<String> fields);

	Boolean hexists(String key, String field);

	String hget(String key, String field);
	
	String hgetString(String key, String field);

	Long hincrby(String key, String field, long amount);

	Double hincrbyfloat(String key, String field, double amount);

	Map<String, String> hgetall(String key);

	Set<String> hkeys(String key);

	Long hlen(String key);

	List<String> hmget(String key, List<String> fields);

	void hmset(String key, Map<String, String> map);

	void hset(String key, String field, String value);

	List<Object> hgetall(List<String> keys);
}
