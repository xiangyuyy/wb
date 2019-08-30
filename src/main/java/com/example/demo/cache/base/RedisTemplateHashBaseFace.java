package com.example.demo.cache.base;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import com.sun.istack.internal.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;


public class RedisTemplateHashBaseFace implements ICacheHashBaseFace {

	@Resource
	public RedisTemplate<String, String> redisTemplate;

	@Autowired
	public RedisTemplate<String, String> redisStringValueTemplate;

	@Override
	public Long hdel(String key, String fields) {
		return redisTemplate.opsForHash().delete(key, fields);
	}

	@Override
	public Long hdel(String key, List<String> fields) {
		return redisTemplate.opsForHash().delete(key, fields.toArray(new String[fields.size()]));
	}

	@Override
	public Boolean hexists(String key, String field) {
		return redisTemplate.opsForHash().hasKey(key, field);
	}

	@Override
	public String hget(String key, String field) {
		return (String) redisTemplate.opsForHash().get(key, field);
	}

	@Override
	public String hgetString(String key, String field) {
		return (String) redisStringValueTemplate.opsForHash().get(key, field);
	}
	
	@Override
	public Long hincrby(String key, String field, long amount) {
		return redisStringValueTemplate.opsForHash().increment(key, field, amount);
	}

	@Override
	public Double hincrbyfloat(String key, String field, double amount) {
		return redisStringValueTemplate.opsForHash().increment(key, field, amount);
	}

	@Override
	public Map<String, String> hgetall(String key) {
		HashOperations<String, String, String> ops = redisTemplate.opsForHash();
		return ops.entries(key);
	}

	public List<Object> hgetall(List<String> keys) {
		List<Object> result = redisTemplate.executePipelined(new RedisCallback<List<Object>>() {
			@Nullable
			@Override
			public List<Object> doInRedis(RedisConnection connection) throws DataAccessException {
				for (String key : keys) {
					connection.hGetAll(key.getBytes());
				}
				return null;
			}
		}, redisTemplate.getValueSerializer());
		return result;
	}

	@Override
	public Set<String> hkeys(String key) {
		HashOperations<String, String, String> ops = redisTemplate.opsForHash();
		return ops.keys(key);
	}

	@Override
	public Long hlen(String key) {
		return redisTemplate.opsForHash().size(key);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<String> hmget(String key, List<String> fields) {
		List<Object> list = (List<Object>) (List) fields;
		return (List<String>) (List) redisTemplate.opsForHash().multiGet(key, list);
	}

	@Override
	public void hmset(String key, Map<String, String> map) {
		redisTemplate.opsForHash().putAll(key, map);
	}

	@Override
	public void hset(String key, String field, String value) {
		redisTemplate.opsForHash().put(key, field, value);
	}



}
