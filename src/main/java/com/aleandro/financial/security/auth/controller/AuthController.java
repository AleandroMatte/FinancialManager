package com.aleandro.financial.security.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aleandro.financial.security.auth.requestsdata.AuthenticateRequest;
import com.aleandro.financial.security.auth.requestsdata.AuthenticationResponse;
import com.aleandro.financial.security.auth.requestsdata.RegisterRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequestMapping(path = "/auth")
public class AuthController {

	public AuthController() {
		// TODO Auto-generated constructor stub
	}
	
	@PostMapping("/register")
	public ResponseEntity<AuthenticationResponse> register(
			@RequestBody RegisterRequest request) {
		return null;
	}
	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticationResponse> authenticate(
			@RequestBody AuthenticateRequest request) {
		return null;
	}
	

}
