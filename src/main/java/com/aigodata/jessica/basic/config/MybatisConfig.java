package com.aigodata.jessica.basic.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;

import com.aigodata.jessica.basic.domain.Base;

import tk.mybatis.spring.annotation.MapperScan;

@Configuration
@MapperScan(annotationClass = Repository.class, basePackages = "com", sqlSessionTemplateRef = "sqlSessionTemplate")
public class MybatisConfig {

	@Value("${spring.application.name}")
	private String serviceName;

	@Bean
	public SqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory)
			throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory);
	}

	@Bean
	public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource") DataSource dataSource) throws Exception {
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setVfs(SpringBootVFS.class);
		sessionFactory.setDataSource(dataSource);
		sessionFactory.setTypeAliasesSuperType(Base.class);
		sessionFactory.setTypeAliasesPackage(serviceName);
		org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
		// MyBatis查询结果自动映射(下划线驼峰自动转换)
		configuration.setMapUnderscoreToCamelCase(true);
		// 打印SQL
		configuration.setLogImpl(org.apache.ibatis.logging.stdout.StdOutImpl.class);
		sessionFactory.setConfiguration(configuration);
		return sessionFactory.getObject();
	}

}
