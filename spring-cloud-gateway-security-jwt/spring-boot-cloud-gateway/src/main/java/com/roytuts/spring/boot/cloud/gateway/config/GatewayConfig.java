package com.roytuts.spring.boot.cloud.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.roytuts.spring.boot.cloud.gateway.filter.JwtAuthenticationFilter;

@Configuration
public class GatewayConfig {

	@Autowired
	private JwtAuthenticationFilter filter;

	@Bean
	public RouteLocator routes(RouteLocatorBuilder builder) {
		
		/*uri("lb://alert")*/
		return builder.routes().route("auth", r -> r.path("/auth/**").filters(f -> f.filter(filter)).uri("http://localhost:3100"))
				.route("alert", r -> r.path("/alert/**").filters(f -> f.filter(filter)).uri("http://localhost:7000"))
				.route("echo", r -> r.path("/echo/**").filters(f -> f.filter(filter)).uri("http://localhost:9000"))
				.route("hello", r -> r.path("/hello/**").filters(f -> f.filter(filter)).uri("http://localhost:8000")).build();
			
	}

}
