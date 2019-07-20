package com.aigodata.jessica.basic.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.mengxianun.core.DefaultTranslator;
import com.github.mengxianun.core.Translator;

@Configuration
public class AirConfig {

	@Value("${air.config.location:#{null}}")
	private String configFile;

	@Bean
	public Translator translator() {
		return new DefaultTranslator();
	}

}
