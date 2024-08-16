package com.example.AliBaba.ABbackend.Config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import com.example.AliBaba.ABbackend.Security.JwtAuthenticationEntryPoint;
import com.example.AliBaba.ABbackend.Security.JwtAuthenticationFilter;

@Component
public class SecurityConfig {

	@Autowired
	private JwtAuthenticationEntryPoint point;
	
	@Autowired
	private JwtAuthenticationFilter filter;
	
		@Bean
		public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    	http.csrf(csrf -> csrf.disable())    // CSRF configuration
	        	.cors(cors -> cors.configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues()))  // CORS configuration
	        	.authorizeHttpRequests(auth -> auth
	        	.requestMatchers("/Home/**").hasRole("ADMIN")
	            .requestMatchers("/auth/login").permitAll()
	        	.anyRequest().authenticated())  // Allow all requests for all users
	        	.exceptionHandling(ex -> ex.authenticationEntryPoint(point))  // Handle authentication errors
	        	.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));  // Stateless session management
	    	// Stateless sessions for JWT
	    
	    	// Add JWT filter before username/password authentication filter
	    	http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);

	    	return http.build(); 
	}
	



}
