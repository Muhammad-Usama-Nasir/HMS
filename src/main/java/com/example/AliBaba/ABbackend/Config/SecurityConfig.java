package com.example.AliBaba.ABbackend.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import com.example.AliBaba.ABbackend.Security.JwtAuthenticationEntryPoint;
import com.example.AliBaba.ABbackend.Security.JwtAuthenticationFilter;
import com.example.AliBaba.ABbackend.Service.CustomUserDetailsService;

@Component
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
	    	http.csrf(csrf -> csrf.disable())    // CSRF configuration
	        	.cors(cors -> cors.configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues()))  // CORS configuration
	        	.authorizeHttpRequests(auth -> auth
	        		.requestMatchers("Home/**").authenticated()
//	        		.requestMatchers("/Home/**").hasRole("USER")
	        		.requestMatchers("/auth/signup").permitAll()
	            	.requestMatchers("/auth/login").permitAll()
	        		.anyRequest().authenticated())   // Any other requests must be authenticated
	        	.exceptionHandling(ex -> ex.authenticationEntryPoint(point))  // Handle authentication errors
	        	.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));  // Stateless session management
	    	// Stateless sessions for JWT
	    
	    	// Add JWT filter before username/password authentication filter
	    	http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);

	    	return http.build(); 
	}
	
		
		@Bean
		public DaoAuthenticationProvider DaoAuthenticationProvider() {
			DaoAuthenticationProvider daoAuthProvider = new DaoAuthenticationProvider();
			daoAuthProvider.setUserDetailsService(userdetailsService);
			daoAuthProvider.setPasswordEncoder(passwordEncoder);
			return daoAuthProvider;
		}

}
