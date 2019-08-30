package com.example.demo.cache.config;

import org.springframework.cache.Cache;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class AppCacheConfigurer extends CachingConfigurerSupport {

	@Bean
	@Override
	public CacheErrorHandler errorHandler() {
		CacheErrorHandler cacheErrorHandler = new CacheErrorHandler() {
			@Override
			public void handleCacheGetError(RuntimeException e, Cache cache, Object key) {
				log.error("获取缓存时异常---key：{}", key, e);
			}

			@Override
			public void handleCachePutError(RuntimeException e, Cache cache, Object key, Object value) {
				log.error("handleCachePutError缓存时异常---key：{}", key, e);
			}

			@Override
			public void handleCacheEvictError(RuntimeException e, Cache cache, Object key) {
				log.error("handleCacheEvictError缓存时异常---key：-", key, e);
			}

			@Override
			public void handleCacheClearError(RuntimeException e, Cache cache) {
				log.error("清除缓存时异常---：-", e);
			}
		};
		return cacheErrorHandler;
	}
}
