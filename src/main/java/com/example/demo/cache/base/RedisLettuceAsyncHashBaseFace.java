package com.example.demo.cache.base;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import com.example.demo.exception.meta.SkySysUnknownException;
import org.apache.commons.collections.CollectionUtils;


import io.lettuce.core.KeyValue;
import io.lettuce.core.RedisFuture;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.async.RedisAsyncCommands;

public class RedisLettuceAsyncHashBaseFace implements ICacheHashBaseFace {

	@Resource
	private StatefulRedisConnection<String, String> statefulRedisConnection;

	@Override
	public Boolean hexists(String key, String field) {
		RedisAsyncCommands<String, String> redisAsyncCommands = statefulRedisConnection.async();
		RedisFuture<Boolean> result = redisAsyncCommands.hexists(key, field);
		try {
			return result.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
			throw new SkySysUnknownException("redis异步获取数据异常", e);
		}
	}

	@Override
	public String hget(String key, String field) {
		RedisAsyncCommands<String, String> redisAsyncCommands = statefulRedisConnection.async();
		RedisFuture<String> result = redisAsyncCommands.hget(key, field);
		try {
			return result.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
			throw new SkySysUnknownException("redis异步获取数据异常", e);
		}
	}

	@Override
	public Long hincrby(String key, String field, long amount) {
		RedisAsyncCommands<String, String> redisAsyncCommands = statefulRedisConnection.async();
		RedisFuture<Long> result = redisAsyncCommands.hincrby(key, field, amount);
		try {
			return result.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
			throw new SkySysUnknownException("redis异步获取数据异常", e);
		}
	}

	@Override
	public Double hincrbyfloat(String key, String field, double amount) {
		RedisAsyncCommands<String, String> redisAsyncCommands = statefulRedisConnection.async();
		RedisFuture<Double> result = redisAsyncCommands.hincrbyfloat(key, field, amount);
		try {
			return result.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
			throw new SkySysUnknownException("redis异步获取数据异常", e);
		}
	}

	@Override
	public Map<String, String> hgetall(String key) {
		RedisAsyncCommands<String, String> redisAsyncCommands = statefulRedisConnection.async();
		RedisFuture<Map<String, String>> result = redisAsyncCommands.hgetall(key);
		try {
			return result.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
			throw new SkySysUnknownException("redis异步获取数据异常", e);
		}
	}

	@Override
	public Long hlen(String key) {
		RedisAsyncCommands<String, String> redisAsyncCommands = statefulRedisConnection.async();
		RedisFuture<Long> result = redisAsyncCommands.hlen(key);
		try {
			return result.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
			throw new SkySysUnknownException("redis异步获取数据异常", e);
		}
	}

	@Override
	public Long hdel(String key, String fields) {
		RedisAsyncCommands<String, String> redisAsyncCommands = statefulRedisConnection.async();
		RedisFuture<Long> result = redisAsyncCommands.hdel(key, fields);
		try {
			return result.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
			throw new SkySysUnknownException("redis异步获取数据异常", e);
		}
	}

	@Override
	public Long hdel(String key, List<String> fields) {
		RedisAsyncCommands<String, String> redisAsyncCommands = statefulRedisConnection.async();
		RedisFuture<Long> result = redisAsyncCommands.hdel(key, fields.toArray(new String[fields.size()]));
		try {
			return result.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
			throw new SkySysUnknownException("redis异步获取数据异常", e);
		}
	}

	@Override
	public Set<String> hkeys(String key) {
		RedisAsyncCommands<String, String> redisAsyncCommands = statefulRedisConnection.async();
		RedisFuture<List<String>> result = redisAsyncCommands.hkeys(key);
		try {
			List<String> list = result.get();
			return new HashSet<>(list);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
			throw new SkySysUnknownException("redis异步获取数据异常", e);
		}
	}

	@Override
	public List<String> hmget(String key, List<String> fields) {
		RedisAsyncCommands<String, String> redisAsyncCommands = statefulRedisConnection.async();
		RedisFuture<List<KeyValue<String, String>>> result = redisAsyncCommands.hmget(key, fields.toArray(new String[fields.size()]));
		try {
			List<KeyValue<String, String>> list = result.get();
			List<String> rs = new ArrayList<String>();
			if (CollectionUtils.isNotEmpty(list)) {
				for (KeyValue<String, String> keyValue : list) {
					if (keyValue.hasValue()) {
						rs.add(keyValue.getValue());
					} else {
						rs.add(null);
					}
				}
			}
			list = null;
			return rs;
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
			throw new SkySysUnknownException("redis异步获取数据异常", e);
		}
	}

	@Override
	public void hmset(String key, Map<String, String> map) {
		RedisAsyncCommands<String, String> redisAsyncCommands = statefulRedisConnection.async();
		RedisFuture<String> result = redisAsyncCommands.hmset(key, map);
		try {
			result.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
			throw new SkySysUnknownException("redis异步获取数据异常", e);
		}
	}

	@Override
	public void hset(String key, String field, String value) {
		RedisAsyncCommands<String, String> redisAsyncCommands = statefulRedisConnection.async();
		RedisFuture<Boolean> result = redisAsyncCommands.hset(key, field, value);
		try {
			result.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
			throw new SkySysUnknownException("redis异步获取数据异常", e);
		}
	}

	@Override
	public List<Object> hgetall(List<String> keys) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String hgetString(String key, String field) {
		// TODO Auto-generated method stub
		return null;
	}

}
