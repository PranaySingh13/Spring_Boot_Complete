package com.gk;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class LazyLoadingBean {
	
	public LazyLoadingBean() {
		super();
		System.out.println("LazyLoadingBean object created");
	}

}
