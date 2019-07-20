package com.aigodata.jessica.basic.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.session.data.redis.RedisFlushMode;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/***
 * redis集群配置
 * 
 * @author saps
 *
 */
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 600, redisFlushMode = RedisFlushMode.IMMEDIATE)
public class RedisSingleConfig {

	@SuppressWarnings("rawtypes")
	@Bean(name = "redisTemplate")
	public RedisTemplate redisTemplate(RedisConnectionFactory factory) {
		StringRedisTemplate template = new StringRedisTemplate();
		template.setConnectionFactory(factory);
		return template;
	}

}
