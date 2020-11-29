package com.jfjara.meep.meeptest.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jfjara.meep.meeptest.utils.Constants;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfig {

	@Value("${app.description}") 
	private String appDescription;
	
	@Value("${app.version}") 
	private	String appVersion;
	
	@Value("${app.title}")
	private String title;
	
	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI().info(new Info().title(title).version(appVersion)
				.description(appDescription).termsOfService(Constants.SWAGGER_TERMS)
				.license(new License().name(Constants.SWAGGER_LICENSE).url(Constants.SWAGGER_URL)));
	}
}