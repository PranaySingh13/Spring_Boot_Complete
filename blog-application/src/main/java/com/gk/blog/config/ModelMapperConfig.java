package com.gk.blog.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

	/*
	 * Spring Container creates the object of this ModelMapper class when we
	 * autowired this class in our other classes.
	 * 
	 * Basically used to convert the class object into another class object
	 * condition source and destination class properties match based on their name.
	 */
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
