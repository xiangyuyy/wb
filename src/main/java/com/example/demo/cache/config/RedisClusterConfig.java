package com.example.demo.cache.config;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;


import io.lettuce.core.ClientOptions;
import io.lettuce.core.ClientOptions.Builder;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.cluster.ClusterClientOptions;
import io.lettuce.core.cluster.RedisClusterClient;
import io.lettuce.core.cluster.api.StatefulRedisClusterConnection;
import io.lettuce.core.codec.ByteArrayCodec;
import io.lettuce.core.resource.ClientResources;
import io.lettuce.core.resource.DefaultClientResources;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author wangchao
 * @des
 * @date 2018-3-13 上午9:39:14
 */
@Configuration
@EnableCaching
@ConditionalOnClass(RedisClusterConfig.class)
/*@EnableConfigurationProperties(RedisClusterProperties.class)*/
public class RedisClusterConfig {

/*    @Resource
    private RedisClusterProperties redisClusterProperties;*/

/*    @Bean
    public RedisClusterConfiguration redisClusterConfiguration() {
        RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration();
        Set<RedisNode> jedisClusterNodes = new HashSet<RedisNode>();
        for (String node : redisClusterProperties.getNodes().split(",")) {
            String[] parts = StringUtils.split(node, ":");
            jedisClusterNodes.add(new RedisNode(parts[0], Integer.valueOf(parts[1])));
        }
        redisClusterConfiguration.setClusterNodes(jedisClusterNodes);
        return redisClusterConfiguration;
    }*/

    @Bean(name = "jedisConnectionFactory")
    public JedisConnectionFactory jedisConnectionFactory() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(100);
        jedisPoolConfig.setMinIdle(10);
        jedisPoolConfig.setMaxWaitMillis(5);

        JedisConnectionFactory connectionFactory = new JedisConnectionFactory();
        connectionFactory.setPoolConfig(jedisPoolConfig);
        connectionFactory.setDatabase(0);
        connectionFactory.setHostName("127.0.0.1");
        connectionFactory.setPassword("");
        connectionFactory.setPort(6379);
        connectionFactory.setTimeout(0);
        return  connectionFactory;
/*        String str = redisClusterProperties.getNodes().split(",")[0];
        String hostName = StringUtils.split(str, ":")[0];
        Integer port = Integer.valueOf(StringUtils.split(str, ":")[1]);

        JedisConnectionFactory redisConnectionFactory = new JedisConnectionFactory();
        redisConnectionFactory.setHostName(hostName);
        redisConnectionFactory.setPort(port);
        redisConnectionFactory.setTimeout(10000);
        return redisConnectionFactory;*/
    }

    @Bean
    public RedisTemplate<String, String> redisTemplate(JedisConnectionFactory jedisConnectionFactory) {
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<String, String>();
        redisTemplate.setKeySerializer(redisTemplate.getStringSerializer());
        redisTemplate.setHashKeySerializer(redisTemplate.getStringSerializer());

        //和redisStringValueTemplate 的区别的 不加这个string类型的错误获取报错 写入字符串乱码。
        redisTemplate.setHashValueSerializer(redisTemplate.getStringSerializer());
        redisTemplate.setDefaultSerializer(redisTemplate.getStringSerializer());

        redisTemplate.setConnectionFactory(jedisConnectionFactory);
        return redisTemplate;
    }

    @Bean(name = "redisByteTemplate")
    public RedisTemplate<byte[], byte[]> redisByteTemplate(JedisConnectionFactory jedisConnectionFactory) {
        RedisTemplate<byte[], byte[]> redisTemplate = new RedisTemplate<byte[], byte[]>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory);
        return redisTemplate;
    }

    @Bean(name = "redisStringValueTemplate")
    public RedisTemplate<String, String> redisStringValueTemplate(JedisConnectionFactory jedisConnectionFactory) {
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<String, String>();
        redisTemplate.setKeySerializer(redisTemplate.getStringSerializer());
        redisTemplate.setValueSerializer(redisTemplate.getStringSerializer());
        redisTemplate.setHashValueSerializer(redisTemplate.getStringSerializer());
        redisTemplate.setDefaultSerializer(redisTemplate.getStringSerializer());
        redisTemplate.setConnectionFactory(jedisConnectionFactory);
        return redisTemplate;
    }

/*
    @Bean
    public CacheManager cacheManager(RedisTemplate<String, String> redisTemplate) {
        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
        cacheManager.setDefaultExpiration(7 * 24 * 60 * 60L); // 默认缓存7天
        Map<String, Long> expireMap = new HashMap<>();
        expireMap.put("CACHE_USER_RECOMMEND", 5 * 60L);
        expireMap.put(CashConstans.SD_GOODS_BASE_DETAIL, 60L);
        expireMap.put(GoodsStandard.GOODSSTANDGIFT, 60L);
        expireMap.put(UserRealation.SD_USER_RELATION_COUNT, 10 * 60L);
        expireMap.put(UserGMVDto.USER_GMV_DTO, 10 * 60L);
        expireMap.put(CashConstans.CACHE_APP_TEMP, 10L);
        expireMap.put(CashConstans.CACHE_APP_TEMP_LESS, 3L);
        expireMap.put(CashConstans.SD_ACTIVITY, 30L);
        expireMap.put(UserRankRankVO.USER_RANK_RANK, 30L);
        expireMap.put(UserRankTeamRankVO.USER_RANK_TEAM_RANKNO, 30L);
        expireMap.put(UserConstans.USER_RANK_TEAM_LEADER, 30L);
        expireMap.put(CashConstans.LASTBUY_GOODSID_USERID, 60L);
        cacheManager.setExpires(expireMap);
        return cacheManager;
    }
*/

    @Bean
    JdkSerializationRedisSerializer jdkSerializationRedisSerializer() {
        return new JdkSerializationRedisSerializer();
    }

/*    @Bean(destroyMethod = "close", name = "statefulRedisConnection")
    StatefulRedisConnection<String, String> statefulRedisConnection() {
        String str = redisClusterProperties.getNodes().split(",")[0];
        String hostName = StringUtils.split(str, ":")[0];
        Integer port = Integer.valueOf(StringUtils.split(str, ":")[1]);

        RedisURI.Builder builder = RedisURI.builder();
        builder.withHost(hostName).withPort(port);
        RedisURI redisURI = builder.build();
        ClientResources clientResources = DefaultClientResources.create();
        RedisClient client = RedisClient.create(clientResources, redisURI);

        Builder builder2 = ClientOptions.builder();
        builder2.autoReconnect(true);
        client.setOptions(builder2.build());
        return client.connect();
    }*/

/*    @Bean(destroyMethod = "close", name = "byteRedisConnection")
    StatefulRedisConnection<byte[], byte[]> byteRedisConnection() {
        String str = redisClusterProperties.getNodes().split(",")[0];
        String hostName = StringUtils.split(str, ":")[0];
        Integer port = Integer.valueOf(StringUtils.split(str, ":")[1]);

        RedisURI.Builder builder = RedisURI.builder();
        builder.withHost(hostName).withPort(port);
        RedisURI redisURI = builder.build();
        ClientResources clientResources = DefaultClientResources.create();
        RedisClient client = RedisClient.create(clientResources, redisURI);

        Builder builder2 = ClientOptions.builder();
        builder2.autoReconnect(true);
        client.setOptions(builder2.build());
        return client.connect(new ByteArrayCodec());
    }*/

    /**
     *
     *
     *
     *
     *
     * 集群配置 备用
     *
     *
     *
     *
     *
     *
     *
     *
     *
     */
    /**
     * @return
     * @author kjl
     * @date 2018年10月27日
     */
    // @Bean(name = "clusterRedisUri")
/*    RedisURI clusterRedisUri() {
        RedisURI.Builder builder = RedisURI.builder();
        for (String node : redisClusterProperties.getNodes().split(",")) {
            String[] parts = StringUtils.split(node, ":");
            builder.withHost(parts[0]).withPort(Integer.valueOf(parts[1]));
        }
        return builder.build();
    }*/

    // 配置集群选项,自动重连,最多重定型1次
    // @Bean
/*    ClusterClientOptions clusterClientOptions() {
        return ClusterClientOptions.builder().autoReconnect(true).maxRedirects(5).build();
    }

    // 创建集群客户端
    // @Bean
    RedisClusterClient redisClusterClient(ClusterClientOptions clusterClientOptions, RedisURI clusterRedisUri) {
        ClientResources clientResources = DefaultClientResources.create();
        RedisClusterClient redisClusterClient = RedisClusterClient.create(clientResources, clusterRedisUri);
        redisClusterClient.setOptions(clusterClientOptions);
        return redisClusterClient;
    }

    // 集群连接
    // @Bean(destroyMethod = "close")
    StatefulRedisClusterConnection<String, String> statefulRedisClusterConnection(RedisClusterClient redisClusterClient) {
        return redisClusterClient.connect();
    }*/

}
