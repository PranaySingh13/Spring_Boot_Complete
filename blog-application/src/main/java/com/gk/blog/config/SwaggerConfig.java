package com.gk.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	/**
	 * A builder which is intended to be the primary interface into the Springfox framework.
	 * Provides sensible defaults and convenience methods for configuration.
	 */
	//Docket class to inform swagger that we require documentation of type 2 
	@Bean
	public Docket swaggerConfiguration() {

		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("Blog Application")
				.select()
				.paths(PathSelectors.ant("/**"))//url for which controller we require documentation
				.apis(RequestHandlerSelectors.basePackage("com.gk.blog"))
				.build()
				.apiInfo(apiInfo());
	}
	
	private ApiInfo apiInfo() {
		/**
		 * Builds the api information
		 */
		return new ApiInfoBuilder().title("Blog Application")
				.description("Sample Documentation Generateed Using SWAGGER2 for our Blog Application API")
				.termsOfServiceUrl("https://www.youtube.com/playlist?list=PL0zysOflRCen-GihOcm1hZfYAlwr63K_M")
				.license("GK Software Solutions")
				.licenseUrl("https://www.gksoftwaresolutions.com").version("1.1.0").build();
	}

}
//http://localhost:1234/swagger-ui/index.html#/