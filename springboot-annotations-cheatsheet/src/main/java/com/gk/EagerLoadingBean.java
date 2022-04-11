package com.gk;

import org.springframework.stereotype.Component;

@Component
public class EagerLoadingBean {

	public EagerLoadingBean() {
		super();
		System.out.println("EagerLoadingBean object created");
	}

}
