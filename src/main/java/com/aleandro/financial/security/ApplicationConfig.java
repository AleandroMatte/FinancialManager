package com.aleandro.financial.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.aleandro.financial.UserSec.services.UserSecServices;

@Configuration
public class ApplicationConfig {
	
	@Autowired
	private UserSecServices user_service;
	

	public ApplicationConfig() {
		// TODO Auto-generated constructor stub
	}

    @Bean
    public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(user_service);
		authProvider.setPasswordEncoder(passwordEncode());
		return authProvider;
	}
    
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration config)
    		throws Exception {
    	return config.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncode() {
		// TODO Auto-generated method stub
		return new BCryptPasswordEncoder();
	}

}
