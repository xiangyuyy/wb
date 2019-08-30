package com.example.demo.cache.base;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import javax.annotation.Resource;

import com.example.demo.cache.CacheUtil;
import com.example.demo.exception.meta.SkySysUnknownException;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.data.redis.core.ZSetOperations.TypedTuple;



import io.lettuce.core.KeyValue;
import io.lettuce.core.RedisFuture;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.async.RedisAsyncCommands;


public class RedisLettuceAsyncBaseFace extends RedisLettuceAsyncHashBaseFace implements CacheUtil {

	@Resource
	private StatefulRedisConnection<String, String> statefulRedisConnection;

	@Resource
	private StatefulRedisConnection<byte[], byte[]> byteRedisConnection;

	@Override
	public String get(String key) {
		RedisAsyncCommands<String, String> redisAsyncCommands = statefulRedisConnection.async();
		RedisFuture<String> result = redisAsyncCommands.get(key);
		try {
			return result.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
			throw new SkySysUnknownException("redis异步获取数据异常, 方法:get()", e);
		}
	}

	public byte[] get(byte[] key) {
		RedisAsyncCommands<byte[], byte[]> redisAsyncCommands = byteRedisConnection.async();
		RedisFuture<byte[]> result = redisAsyncCommands.get(key);
		try {
			return result.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
			throw new SkySysUnknownException("redis异步获取数据异常, 方法:get()", e);
		}
	}

	@Override
	public Long incr(String key) {
		RedisAsyncCommands<String, String> redisAsyncCommands = statefulRedisConnection.async();
		RedisFuture<Long> result = redisAsyncCommands.incr(key);
		try {
			return result.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
			throw new SkySysUnknownException("redis异步获取数据异常, 方法:incr()", e);
		}
	}

	@Override
	public void set(String key, String value) {
		RedisAsyncCommands<String, String> redisAsyncCommands = statefulRedisConnection.async();
		RedisFuture<String> result = redisAsyncCommands.set(key, value);
		try {
			result.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
			throw new SkySysUnknownException("redis异步获取数据异常, 方法:set()", e);
		}
	}

	@Override
	public void setex(byte[] key, byte[] value, long seconds) {
		RedisAsyncCommands<byte[], byte[]> redisAsyncCommands = byteRedisConnection.async();
		RedisFuture<String> result = redisAsyncCommands.setex(key, seconds, value);
		try {
			result.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
			throw new SkySysUnknownException("redis异步获取数据异常, 方法:get()", e);
		}
	}

	@Override
	public void set(byte[] key, byte[] value) {
		RedisAsyncCommands<byte[], byte[]> redisAsyncCommands = byteRedisConnection.async();
		RedisFuture<String> result = redisAsyncCommands.set(key, value);
		try {
			result.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
			throw new SkySysUnknownException("redis异步获取数据异常, 方法:get()", e);
		}
	}

	@Override
	public void set(String key, String value, long seconds) {
		RedisAsyncCommands<String, String> redisAsyncCommands = statefulRedisConnection.async();
		RedisFuture<String> result = redisAsyncCommands.setex(key, seconds, value);
		try {
			result.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
			throw new SkySysUnknownException("redis异步获取数据异常, 方法:set()", e);
		}
	}

	@Override
	public Integer append(String key, String value) {
		RedisAsyncCommands<String, String> redisAsyncCommands = statefulRedisConnection.async();
		RedisFuture<Long> result = redisAsyncCommands.append(key, value);
		try {
			return Integer.valueOf(result.get().toString());
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
			throw new SkySysUnknownException("redis异步获取数据异常, 方法:append()", e);
		}
	}

	@Override
	public void mset(Map<String, String> map) {
		RedisAsyncCommands<String, String> redisAsyncCommands = statefulRedisConnection.async();
		RedisFuture<String> result = redisAsyncCommands.mset(map);
		try {
			result.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
			throw new SkySysUnknownException("redis异步获取数据异常,", e);
		}
	}

	@Override
	public void setex(String key, long seconds, String value) {
		RedisAsyncCommands<String, String> redisAsyncCommands = statefulRedisConnection.async();
		RedisFuture<String> result = redisAsyncCommands.setex(key, seconds, value);
		try {
			result.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
			throw new SkySysUnknownException("redis异步获取数据异常,", e);
		}
	}

	@Override
	public Boolean setnx(String key, String value) {
		RedisAsyncCommands<String, String> redisAsyncCommands = statefulRedisConnection.async();
		RedisFuture<Boolean> result = redisAsyncCommands.setnx(key, value);
		try {
			return result.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
			throw new SkySysUnknownException("redis异步获取数据异常,", e);
		}
	}

	@Override
	public Boolean expire(String key, long seconds) {
		RedisAsyncCommands<String, String> redisAsyncCommands = statefulRedisConnection.async();
		RedisFuture<Boolean> result = redisAsyncCommands.expire(key, seconds);
		try {
			return result.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
			throw new SkySysUnknownException("redis异步获取数据异常,", e);
		}
	}

	@Override
	public Boolean exists(String key) {
		RedisAsyncCommands<String, String> redisAsyncCommands = statefulRedisConnection.async();
		@SuppressWarnings("deprecation")
		RedisFuture<Long> result = redisAsyncCommands.exists(new String[] { key });
		try {
			Long rs = result.get();
			if (rs != null && rs.longValue() == 1) {
				return true;
			}
			return false;
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
			throw new SkySysUnknownException("redis异步获取数据异常,", e);
		}
	}

	@Override
	public Long incrby(String key, Long value) {
		RedisAsyncCommands<String, String> redisAsyncCommands = statefulRedisConnection.async();
		RedisFuture<Long> result = redisAsyncCommands.incrby(key, value);
		try {
			return result.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
			throw new SkySysUnknownException("redis异步获取数据异常,", e);
		}
	}

	@Override
	public List<String> mget(List<String> keys) {
		RedisAsyncCommands<String, String> redisAsyncCommands = statefulRedisConnection.async();
		RedisFuture<List<KeyValue<String, String>>> result = redisAsyncCommands.mget(keys.toArray(new String[keys.size()]));
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
			throw new SkySysUnknownException("redis异步获取数据异常,", e);
		}
	}

	@Override
	public void del(String key) {
		RedisAsyncCommands<String, String> redisAsyncCommands = statefulRedisConnection.async();
		RedisFuture<Long> result = redisAsyncCommands.del(key);
		try {
			result.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
			throw new SkySysUnknownException("redis异步获取数据异常,", e);
		}
	}

	@Override
	public void del(List<String> keys) {
		RedisAsyncCommands<String, String> redisAsyncCommands = statefulRedisConnection.async();
		RedisFuture<Long> result = redisAsyncCommands.del(keys.toArray(new String[keys.size()]));
		try {
			result.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
			throw new SkySysUnknownException("redis异步获取数据异常,", e);
		}
	}

	@Override
	public void batchDel(String key) {
	}

	@Override
	public void set(String key, long value) {
		
	}

	@Override
	public Boolean zadd(String key, String value, double score) {
		return null;
	}

	@Override
	public Set<String> zrangeByScore(String key, double min, double max) {
		return null;
	}

	@Override
	public Long zadd(String key, Set<TypedTuple<String>> tuples) {
		return null;
	}

	@Override
	public Long zRemove(String key, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getString(String key) {
		// TODO Auto-generated method stub
		return null;
	}
}
