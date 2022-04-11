package com.gk.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = { "classpath:custom.properties" })
public class BeanConfig {

	@Value("${spring.mail.host}")
	private String HOST;

	@Value("${spring.mail.username}")
	private String USERNAME;

	@Value("${spring.mail.port}")
	private String PORT;

	@Value("${greet.message}")
	private String message;

	@Bean
	public void testBean() {

		System.out.println(message);

		// @Value check values for respective key in application.properties
		System.out.println("Mail Properties load using @Value: " + USERNAME + " " + HOST + " " + PORT);

	}

}
