package com.main.logicgate.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Service;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final AuthenticationFilter jwtAuthFilter;
    public final AuthenticationProvider authenticationProvider;
    private static final String[] WHITE_LIST_URLS = {
            "/articles/all",
            "/register",
            "/api/v1/auth/**",
            "/articles/{articleId}"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.cors(AbstractHttpConfigurer::disable)

                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
                        .requestMatchers(WHITE_LIST_URLS)
                        .permitAll()
                        .anyRequest().permitAll()
                )
                .sessionManagement(sessionManagement -> sessionManagement
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .authenticationProvider(authenticationProvider);
        return http.build();
    }

//    @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(Arrays.asList("https://logicgatesblog-9da8a03a3c58.herokuapp.com/articles/all", "https://logicgatesblog-9da8a03a3c58.herokuapp.com/api/v1/auth/register" ));
////        configuration.addAllowedOrigin("*");
//        configuration.setAllowedMethods(Arrays.asList("GET"));
//        configuration.setAllowedHeaders(List.of("Authorization"));
//        configuration.setAllowedHeaders(Arrays.asList("Access-Control-Allow-Origin"));
//        configuration.setAllowedHeaders(Arrays.asList("authorization", "content-type", "x-auth-token"));
//        configuration.setExposedHeaders(Arrays.asList("x-auth-token"));
////        configuration.addAllowedHeader("*");
////        configuration.addAllowedMethod("*");
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }
//@Bean
//CorsConfigurationSource corsConfigurationSource() {
//    CorsConfiguration configuration = new CorsConfiguration();
//    configuration.setAllowedOrigins(Arrays.asList("https://logicgatesblog-9da8a03a3c58.herokuapp.com"));
//    configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
//    configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type", "x-auth-token"));
//    configuration.setExposedHeaders(Arrays.asList("x-auth-token"));
//    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//    source.registerCorsConfiguration("/**", configuration);
//    return source;
//}
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        System.out.println("TEST ");
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("https://logicgatesblog-9da8a03a3c58.herokuapp.com")); // Set your allowed origins
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS")); // Include necessary methods
//        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type", "x-auth-token")); // Include necessary headers
        configuration.setAllowedHeaders(Arrays.asList(
                "Authorization",
                "Content-Type",
                "Accept",
                "Origin",
                "X-Requested-With",
                "X-Auth-Token",
                "If-Match",
                "If-None-Match",
                "Cookie",
                "User-Agent",
                "Referer"
        ));
        configuration.addAllowedHeader("Content-Type");
        configuration.setExposedHeaders(Arrays.asList("x-auth-token")); // Expose any additional headers if needed
        configuration.setAllowCredentials(true); // Allow credentials like cookies to be sent
        /// start
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        /// end
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
