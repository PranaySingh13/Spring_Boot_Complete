package com.gk.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

	/*
	 * Return a logger named corresponding to the class passed as parameter,using
	 * the statically bound ILoggerFactory instance.
	 */
	Logger log = LoggerFactory.getLogger(DemoController.class);

	/*
	 * Difference between slf4j and log4j
	 * 
	 * In SLf4j, we directly pass the arguments.
	 * 
	 * log.debug("Request{}",name);
	 * 
	 * In log4j, we concat the arguments as in String so it takes memory.
	 * 
	 * log.debug("Request{}"+name);
	 */

	@GetMapping("/test/{name}")
	public String greeting(@PathVariable String name) {
		log.debug("Request {}", name);
		if (name.equalsIgnoreCase("test"))
			throw new RuntimeException("Oops Exception Raised!.. ");
		String response = "Hi " + name + " Welcome";
		log.debug("Response {}", response);
		return response;
	}

}
