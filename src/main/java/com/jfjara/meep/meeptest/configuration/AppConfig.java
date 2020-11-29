package com.jfjara.meep.meeptest.configuration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.jfjara.meep.meeptest.cache.MeepResourceCache;
import com.jfjara.meep.meeptest.errors.RestTemplateErrorHandler;

@Configuration
public class AppConfig {

	@Bean
	@Scope("singleton")
	public MeepResourceCache cache() {
		return new MeepResourceCache();
	}

	@Bean
	@Scope("prototype")
	public RestTemplate restTemplate(ClientHttpRequestFactory factory) {
		return new RestTemplate(factory);
	}
	
	@Bean
	public RestTemplate customRestTemplate(RestTemplateBuilder builder) {
		return builder.errorHandler(new RestTemplateErrorHandler()).build();
	}

}
