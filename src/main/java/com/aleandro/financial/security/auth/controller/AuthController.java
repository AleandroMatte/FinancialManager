package com.aleandro.financial.security.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aleandro.financial.security.auth.requestsdata.AuthenticateRequest;
import com.aleandro.financial.security.auth.requestsdata.AuthenticationResponse;
import com.aleandro.financial.security.auth.requestsdata.RegisterRequest;
import com.aleandro.financial.security.auth.services.AuthenticationServices;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequestMapping(path = "/auth")
public class AuthController {

	@Autowired
	private AuthenticationServices services;
	
	
	public AuthController() {
		// TODO Auto-generated constructor stub
	}
	
	@PostMapping(path = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AuthenticationResponse> register(
			@RequestBody RegisterRequest request) {
		return ResponseEntity.ok(services.register(request));
	}
	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticationResponse> authenticate(
			@RequestBody AuthenticateRequest request) {
		return ResponseEntity.ok(services.authenticate(request));
	}
	

}
