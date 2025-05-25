package com.store.book.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfiguration {

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI().info(new Info().title("Bookstore API – Buy & Save Online").description(
				"Get the best software development books with special discounts from the store when buying multiple copies")
				.version("1.0"));
	}

}
