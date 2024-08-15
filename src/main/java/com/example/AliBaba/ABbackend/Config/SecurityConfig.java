package com.example.AliBaba.ABbackend.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

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
		
		http.csrf(csrf -> csrf.disable()) 	//csrf configuration
		    .cors(cors -> cors.disable()) 	//cors configration
		    .authorizeHttpRequests(auth -> auth.requestMatchers("/home/**").hasRole("ADMIN")
		    								   .requestMatchers("/auth/login").permitAll().anyRequest().authenticated())
		    .exceptionHandling(ex -> ex.authenticationEntryPoint(point))
		    .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));  //STATELESS is used because we dont have to store anything on our database
		
		http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
		return http.build();	 
	}
}
