package com.gk.blog;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}

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
