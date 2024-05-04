package com.aleandro.financial.security.auth.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.aleandro.financial.User.models.User;
import com.aleandro.financial.UserSec.infra.models.UserSecModel;
import com.aleandro.financial.UserSec.repositories.UserSecRepository;
import com.aleandro.financial.security.auth.requestsdata.AuthenticateRequest;
import com.aleandro.financial.security.auth.requestsdata.AuthenticationResponse;
import com.aleandro.financial.security.auth.requestsdata.RegisterRequest;

@Service
public class AuthenticationServices {
	
	@Autowired
	private UserSecRepository user_repo;

	public AuthenticationServices() {
	}

	public AuthenticationResponse register(RegisterRequest request) {
		UserSecModel user = new UserSecModel();
		user.setFull_name(request.getFirst_name() + " "+ request.getLast_name());
		user.setUser_name(request.getUser_name());
		
		return null;
	}

	public AuthenticationResponse authenticate(AuthenticateRequest request) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	

}
