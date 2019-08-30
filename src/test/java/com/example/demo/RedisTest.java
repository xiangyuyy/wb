package com.example.demo;

import com.example.demo.cache.CacheUtil;
import lombok.var;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {
    @Resource
    private CacheUtil cacheUtil;
    @Test
    public void testRedis(){
        cacheUtil.set("key12","key121212");
     /*   String key = cacheUtil.getString("mykey");*/
       /* System.out.println("key =1111 " + key);*/
    }
}
