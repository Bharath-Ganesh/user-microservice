package com.xyz.user;

import java.util.Collections;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class UserApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);

	}

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.xyz.user")).paths(PathSelectors.any()).build()
				.apiInfo(apiInfo());
	}

	private static ApiInfo apiInfo() {
		return new ApiInfo("User Microservice",
				"XYZ User Microservice manages the profile of both admin users and delivery agents of the back office. Admin users are those who uses and manages the XYZ parcel delivery system based on thier roles associated with user microservice. Delivery agent are users who uses this platform to view their assigned orders. Following are the api's which are a part of user microservice \r\n"
						

					,
						
				"1.0.0", null, null, null, null, Collections.emptyList());
	}

}
