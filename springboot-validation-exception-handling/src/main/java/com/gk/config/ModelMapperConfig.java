package com.gk.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

	/*
	 * we’ve seen how to configure our own ModelMapper bean, so that we can use the
	 * latest version of ModelMapper without relying on Starters which might not be
	 * maintained.
	 */
	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper;
	}

}
