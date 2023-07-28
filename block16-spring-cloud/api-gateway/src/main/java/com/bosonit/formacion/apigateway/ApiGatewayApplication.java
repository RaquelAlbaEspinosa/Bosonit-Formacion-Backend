package com.bosonit.formacion.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

	@Bean
	public RouteLocator myRoutes (RouteLocatorBuilder builder) {
		return builder.routes()
				.route(p -> p
						.path("/ticket/**")
						.uri("http://back-front:8080"))
				.route(p -> p
						.path("/client/**")
						.uri("http://back:8081"))
				.route(p -> p
						.path("/trip/**")
						.uri("http://back:8081"))
				.build();
	}

}
