package com.main.logicgate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Arrays;
import java.util.Collections;

@EntityScan
@SpringBootApplication
@Configuration
@EnableWebSecurity
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

//	@Bean
//	public CorsFilter corsFilter() {
//		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//		CorsConfiguration config = new CorsConfiguration();
//		config.addAllowedOrigin("*");
//		config.addAllowedHeader("*");
//		config.addAllowedMethod("OPTIONS");
//		config.addAllowedMethod("HEAD");
//		config.addAllowedMethod("GET");
//		config.addAllowedMethod("PUT");
//		config.addAllowedMethod("POST");
//		config.addAllowedMethod("DELETE");
//		config.addAllowedMethod("PATCH");
//		config.setAllowCredentials(true);
//		config.setAllowedHeaders(Arrays.asList("Access-Control-Allow-Origin"));
//		config.setAllowedHeaders(Arrays.asList("authorization", "content-type", "x-auth-token"));
//		config.setExposedHeaders(Arrays.asList("x-auth-token"));
//
//		config.setAllowedHeaders(Collections.singletonList("*"));
//		config.setAllowedOrigins(Collections.singletonList("*"));
////		config.addAllowedOrigin("*");
////		config.addAllowedHeader("*");
////		config.addAllowedMethod("GET");
////		config.addAllowedMethod("POST");
////		config.addAllowedMethod("PUT");
////		config.addAllowedMethod("DELETE");
////		config.addAllowedMethod("OPTIONS");
//		config.setAllowedOrigins(Arrays.asList("https://logicgatesblog-9da8a03a3c58.herokuapp.com/"));
//		source.registerCorsConfiguration("/**", config);
//
//
//		return new CorsFilter(source);
//	}
}
