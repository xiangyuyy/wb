/*
package com.example.demo.cache.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

*/
/**
 * @author wangchao
 * @des
 * @date 2018-3-13 上午9:38:29
 *//*

@Configuration
//@PropertySource("classpath:redis.properties")
@ConfigurationProperties(prefix = "spring.redis.cluster")
public class RedisClusterProperties {
    */
/** redis集群节点 *//*

    @Value("${spring.redis.cluster.nodes}")
    private String nodes;
    
    */
/** 连接超时时间 *//*

    @Value("${spring.redis.cluster.timeout}")
    private Integer timeout;
    
    */
/** 重连次数 *//*

    @Value("${spring.redis.cluster.maxAttempts}")
    private Integer maxAttempts;

    public String getNodes() {
	return nodes;
    }

    public void setNodes(String nodes) {
	this.nodes = nodes;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public Integer getMaxAttempts() {
        return maxAttempts;
    }

    public void setMaxAttempts(Integer maxAttempts) {
        this.maxAttempts = maxAttempts;
    }

}
*/
