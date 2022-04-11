package com.gk.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Configuration
@ConfigurationProperties(prefix = "spring.mail")
@Getter
@Setter
@ToString
public class ConfigProperties {

	private String HOST;

	private String USERNAME;

	private String PORT;
}
