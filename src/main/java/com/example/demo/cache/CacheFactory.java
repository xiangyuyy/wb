package com.example.demo.cache;

import com.example.demo.utils.SpringConfigTool;
public class CacheFactory {

	private static CacheUtil cacheUtil;

	public static CacheUtil getUtil() {
		if (cacheUtil == null) {
			cacheUtil = (CacheUtil) SpringConfigTool.getBean("redisTemplateCacheBaseFace");
		}
		return cacheUtil;
	}
}
