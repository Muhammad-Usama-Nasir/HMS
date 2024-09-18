package com.example.AliBaba.ABbackend.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import com.example.AliBaba.ABbackend.Security.JwtAuthenticationEntryPoint;
import com.example.AliBaba.ABbackend.Security.JwtAuthenticationFilter;
import com.example.AliBaba.ABbackend.Service.CustomUserDetailsService;
@EnableMethodSecurity
@Component
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private JwtAuthenticationEntryPoint point;
	
	@Autowired
	private JwtAuthenticationFilter filter;
	
	@Autowired
	private CustomUserDetailsService userdetailsService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
		@Bean
		public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		    http
		        .csrf(csrf -> csrf.disable())                              // CSRF configuration
		        .cors(cors -> cors.configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues())) // CORS config
		        .authorizeHttpRequests(auth -> auth
		            .requestMatchers("Home/**").permitAll()
		            .requestMatchers("/auth/signup").permitAll()
		            .requestMatchers("/auth/login").permitAll()
		            .anyRequest().permitAll()              // Any other requests must be authenticated
		        )
		        .exceptionHandling(ex -> ex.authenticationEntryPoint(point)) // Handle auth errors (**before sessionManagement**)
		        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)); 

		    // Add JWT filter before username/password authentication filter
		    http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class); 

		    return http.build(); // Remember to build and return the SecurityFilterChain
		}
	
		
		@Bean
		public DaoAuthenticationProvider DaoAuthenticationProvider() {
			DaoAuthenticationProvider daoAuthProvider = new DaoAuthenticationProvider();
			daoAuthProvider.setUserDetailsService(userdetailsService);
			daoAuthProvider.setPasswordEncoder(passwordEncoder);
			return daoAuthProvider;
		}

}
