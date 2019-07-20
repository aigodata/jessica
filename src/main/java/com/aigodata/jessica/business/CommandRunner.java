package com.aigodata.jessica.business;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandRunner implements CommandLineRunner {
	protected Logger logger = LoggerFactory.getLogger(CommandRunner.class);

	@Override
	public void run(String... args) throws Exception {
		
	}
}
