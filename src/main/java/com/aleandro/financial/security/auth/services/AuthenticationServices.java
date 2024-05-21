package com.aleandro.financial.security.auth.services;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.aleandro.financial.UserSec.infra.models.UserSecModel;
import com.aleandro.financial.UserSec.services.UserSecServices;
import com.aleandro.financial.permissions.infra.models.Permissions;
import com.aleandro.financial.permissions.services.PermissionService;
import com.aleandro.financial.security.auth.requestsdata.AuthenticateRequest;
import com.aleandro.financial.security.auth.requestsdata.AuthenticationResponse;
import com.aleandro.financial.security.auth.requestsdata.RegisterRequest;
import com.aleandro.financial.security.jwt.service.JwtService;

@Service
public class AuthenticationServices {
	
	@Autowired
	private PasswordEncoder pass_encoder;
	@Autowired
	private JwtService jwt_service;
	@Autowired
	private PermissionService permissionService;
	
	@Autowired
	private UserSecServices user_service;
	
	public AuthenticationServices() {
	}

	public AuthenticationResponse register(RegisterRequest request) {
		UserSecModel user = new UserSecModel();
		user.setFull_name(request.getFirst_name() + " "+ request.getLast_name());
		user.setUser_name(request.getUser_name());
		user.setPassword(pass_encoder.encode(request.getPassword()));
		Permissions user_role = permissionService.get_permissions_by_description().get(request.getRole());
		user.setPermissions(Collections.singletonList(user_role));
		user  = user_service.register_user(user);
		var token = jwt_service.generateToken(generate_user_claims(user),user);
		AuthenticationResponse auth_response = new AuthenticationResponse(token);
		
		return auth_response;
	}

	public AuthenticationResponse authenticate(AuthenticateRequest request) {
		UserSecModel user =  user_service.loadUserByUsername(request.getUsername());
		if(user == null) {
			throw new AuthenticationCredentialsNotFoundException("Incorrect login or password");
		}
		if (!(pass_encoder.matches(request.getPassword(),user.getPassword()))) {
			throw new AuthenticationCredentialsNotFoundException("Incorrect login or password");
		}
		
		
		return new AuthenticationResponse(jwt_service.generateToken(generate_user_claims(user),user));
	}
	
	public static Map<String, Object> generate_user_claims(UserSecModel user){
		Map<String, Object> claims = new HashMap<>();
		claims.put("User_id", user.getId());
		claims.put("User role", user.getPermissions());
		claims.put("User full name", user.getUser_name());
		return claims;
	}
	
	
	

}
