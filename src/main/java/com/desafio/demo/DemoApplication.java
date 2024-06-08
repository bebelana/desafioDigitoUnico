package com.desafio.demo;

import org.springdoc.core.GroupedOpenApi;
import org.springdoc.core.SwaggerUiConfigParameters;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public GroupedOpenApi groupedOpenApi() {
		return GroupedOpenApi.builder()
				.group("api")
				.pathsToMatch("/api/**")
				.build();
	}


}
