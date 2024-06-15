package com.aleandro.financial.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import com.aleandro.financial.security.filters.PathParamInserterFilter;
import com.aleandro.financial.security.jwt.Filter.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private JwtAuthenticationFilter jwtAuthFilter;
	@Autowired
	private AuthenticationProvider auth_provider;
	@Autowired
	private PathParamInserterFilter path_param_inserter;

	public SecurityConfig() {
	}


    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
		.csrf((csrf) -> csrf.disable())
		.cors((cors) ->cors.disable())
		.authorizeHttpRequests((authorizeHttpRequests) ->
	    authorizeHttpRequests
	    .requestMatchers("/error").permitAll()
	    .requestMatchers("/auth/authenticate","/auth/register","/swagger-ui/index.html","/swagger-ui/**","/v3/api-docs/**").permitAll()
	    .requestMatchers("/user/**").authenticated())
		.sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.authenticationProvider(auth_provider)
		.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
		.addFilterAfter(path_param_inserter, UsernamePasswordAuthenticationFilter.class);
		


			
		return http.build();
	}
    
    


}
