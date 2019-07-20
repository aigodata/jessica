package com.aigodata.jessica.basic.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * 数据源配置
 * 
 * @author mengxiangyun
 *
 */
@Configuration
public class DataSourceConfig {

	/**
	 * Druid 数据库连接池
	 * 
	 * @return
	 */
	@Bean
	@ConfigurationProperties(prefix = "spring.datasource.mysql")
	public DataSource dataSource() {
		return (DruidDataSource) DataSourceBuilder.create().type(DruidDataSource.class).build();
	}

}
