package com.aigodata.jessica.basic.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.aigodata.jessica.basic.common.web.bind.JsonParamMethodArgumentResolver;

/**
 * 添加额外的MVC配置(拦截器, 格式化器, 视图控制器等)
 * 
 * @author mengxiangyun
 *
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	/**
	 * 添加自定义方法参数解析器
	 */
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(new JsonParamMethodArgumentResolver(false));
	}

}
