package com.jfjara.meep.meeptest.configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MeepExecutor {

	@Bean
	public ExecutorService executor() {		
		return Executors.newCachedThreadPool();
	}
	
}
