package com.aigodata.jessica.basic.config;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import com.aigodata.jessica.basic.common.shiro.filter.AjaxSessionTimeoutFilter;
import com.aigodata.jessica.basic.common.shiro.filter.JsonAuthenticationFilter;
import com.aigodata.jessica.basic.common.shiro.realm.UserAuthRealm;

/**
 * Shiro配置
 * 
 * @author mengxiangyun
 *
 */
@Configuration
public class ShiroConfig {
	/*
	 * 缓存配置文件
	 */
	@Value("${shiro.cache.configFile}")
	private String cacheManagerConfigFile;
	/*
	 * Session 过期时间
	 */
	@Value("${shiro.session.globalSessionTimeout}")
	private long globalSessionTimeout;
	/*
	 * 定时校验
	 */
	@Value("${shiro.session.sessionValidationSchedulerEnabled}")
	private boolean sessionValidationSchedulerEnabled;

	@Value("${spring.redis.host}")
	private String redisHost;
	@Value("${spring.redis.password}")
	private String redisPassword;
	@Value("${spring.redis.port}")
	private int redisPort;

	private static final String CACHE_KEY = "shiro:cache:";
	private static final String SESSION_KEY = "shiro:token:";
	private static final String NAME = "token";

	/**
	 * Shiro 过滤器
	 * 
	 * @return
	 */
//	@Bean
	public ShiroFilterFactoryBean shiroFilter() {
		ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
		bean.setLoginUrl("/login");
		bean.setSecurityManager(securityManager());

		// 添加自定义过滤器
		Map<String, Filter> filters = bean.getFilters();
		filters.put("ajaxSessionTimeout", new AjaxSessionTimeoutFilter());
		filters.put("jsonAuth", new JsonAuthenticationFilter());
		bean.setFilters(filters);

		// 过滤器链, Shiro权限认证有顺序, 所以这里采用LinkedHashMap
		LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();

		// 不拦截登出请求
		filterChainDefinitionMap.put("/logout", "anon");
		// 不拦截验证码请求
		filterChainDefinitionMap.put("/captcha", "anon");

		// 认证所有请求
		filterChainDefinitionMap.put("/**", "ajaxSessionTimeout, jsonAuth, authc");
		bean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return bean;
	}

	/**
	 * SecurityManager
	 * 
	 * @return
	 */
	@Bean
	public DefaultWebSecurityManager securityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(userAuthRealm());
		// shiro-cache
		//securityManager.setSessionManager(sessionManager());
		//securityManager.setCacheManager(cacheManager());
		// shiro-redis集群-cache
		//securityManager.setSessionManager(sessionManager(redisSessionDAO(redisManager()), simpleCookie()));
		//securityManager.setCacheManager(redisCacheManager(redisManager()));
		// shiro-redis-单机-cache
		securityManager.setSessionManager(sessionManager(redisSessionDAO(redisSingleManager()), simpleCookie()));
		securityManager.setCacheManager(redisCacheManager(redisSingleManager()));
		return securityManager;
	}

	/***
	 * shiro-redis Cluster 开始
	 * 
	@Bean
	public RedisClusterManager redisManager() {
		RedisClusterManager redisManager = new RedisClusterManager();
		redisManager.setHost(redisCluster);
		return redisManager;
	}
	@Bean
	public RedisCacheManager redisCacheManager(RedisClusterManager redisManager) {
		RedisCacheManager redisCacheManager = new RedisCacheManager();
		redisCacheManager.setRedisManager(redisManager);
		redisCacheManager.setExpire(86400);// 单位秒
		redisCacheManager.setKeyPrefix(CACHE_KEY);
		return redisCacheManager;
	}
	@Bean
	public RedisSessionDAO redisSessionDAO(RedisClusterManager redisManager) {
		RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
		redisSessionDAO.setExpire(86400);// 单位秒
		redisSessionDAO.setKeyPrefix(SESSION_KEY);
		redisSessionDAO.setRedisManager(redisManager);
		return redisSessionDAO;
	}
	****/
	
	
	@Bean
	public RedisManager redisSingleManager() {
		RedisManager redisManager = new RedisManager();
		redisManager.setHost(redisHost);
		redisManager.setPassword(redisPassword);
		redisManager.setPort(redisPort);
		return redisManager;
	}

	@Bean
	public RedisCacheManager redisCacheManager(RedisManager redisManager) {
		RedisCacheManager redisCacheManager = new RedisCacheManager();
		redisCacheManager.setRedisManager(redisManager);
		redisCacheManager.setExpire(86400);// 单位秒
		redisCacheManager.setKeyPrefix(CACHE_KEY);
		return redisCacheManager;
	}
	

	@Bean
	public RedisSessionDAO redisSessionDAO(RedisManager redisManager) {
		RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
		redisSessionDAO.setExpire(86400);// 单位秒
		redisSessionDAO.setKeyPrefix(SESSION_KEY);
		redisSessionDAO.setRedisManager(redisManager);
		return redisSessionDAO;
	}
	

	@Bean
	public DefaultWebSessionManager sessionManager(RedisSessionDAO sessionDAO, SimpleCookie simpleCookie) {
		DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
		sessionManager.setSessionDAO(sessionDAO);
		sessionManager.setSessionIdCookieEnabled(true);
		sessionManager.setSessionIdCookie(simpleCookie);
		return sessionManager;
	}

	@Bean
	public SimpleCookie simpleCookie() {
		SimpleCookie simpleCookie = new SimpleCookie();
		simpleCookie.setName(NAME);
		simpleCookie.setPath("/");
		return simpleCookie;
	}

	/***
	 * shiro-redis-cache 结束
	 * 
	 * @return
	 */

	/***
	 * shiro-cache 开始
	 *
	 
	@Bean
	public DefaultWebSessionManager sessionManager() {
		DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
		sessionManager.setCacheManager(cacheManager());
		sessionManager.setGlobalSessionTimeout(globalSessionTimeout);
		sessionManager.setSessionValidationSchedulerEnabled(sessionValidationSchedulerEnabled);
		return sessionManager;
	}

	@Bean
	public EhCacheManager cacheManager() {
		EhCacheManager cacheManager = new EhCacheManager();
		cacheManager.setCacheManagerConfigFile(cacheManagerConfigFile);
		return cacheManager;
	}
	
	*
	* shiro-cache 结束
	*/


	/**
	 * Realm
	 * 
	 * @return
	 */
	@Bean
	public UserAuthRealm userAuthRealm() {
		UserAuthRealm authRealm = new UserAuthRealm();
		HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
		credentialsMatcher.setHashAlgorithmName("MD5"); // 加密算法
		credentialsMatcher.setHashIterations(1024); // 加密次数
		authRealm.setCredentialsMatcher(credentialsMatcher);
		// 缓存名称, 对应EhCache中的名称
		authRealm.setAuthenticationCacheName("authenticationCache");
		authRealm.setAuthorizationCacheName("authorizationCache");
		return authRealm;
	}

	/**
	 * Shiro生命周期处理器. 这里使用static, 否则无法获取到配置参数的值
	 * 
	 * @return
	 */
	@Bean
	public static LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}

	/**
	 * 启用Shiro 注解功能
	 * 
	 * @return
	 */
	@DependsOn("lifecycleBeanPostProcessor")
	@Bean
	public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
		return new DefaultAdvisorAutoProxyCreator();
	}

	/**
	 * 启用Shiro 注解功能
	 * 
	 * @return
	 */
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
		return authorizationAttributeSourceAdvisor;
	}

}
