package com.insursa.config;

import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
@EnableWebSecurity
public class SecurityConfig {   
    @Bean
    @Order(1)
    public SecurityFilterChain basicAuthSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
    	httpSecurity.securityMatcher((HttpServletRequest request) -> {
			return Optional.ofNullable(request.getHeader(HttpHeaders.AUTHORIZATION)).map(h -> {
				return h.toLowerCase().startsWith("bearer ");
			}).orElse(false);
		});

		httpSecurity.csrf(csrf -> csrf.disable()).authorizeHttpRequests(authorize -> authorize
				.requestMatchers("/api/**").authenticated())
		.oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));
	
		return httpSecurity.build();
    }

    @Bean
    @Order(2)
    public SecurityFilterChain oauth2SecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.csrf(csrf -> csrf.disable()).authorizeHttpRequests(request -> request
			.requestMatchers("/**").authenticated())
			.formLogin(form -> {form.loginPage("/login").permitAll();});
		
		return httpSecurity.build();
    }
    
    @Bean
	public JwtDecoder jwtDecoder() {
		return NimbusJwtDecoder.withJwkSetUri("http://127.0.0.1/oauth2/jwks").build();
	}
}
