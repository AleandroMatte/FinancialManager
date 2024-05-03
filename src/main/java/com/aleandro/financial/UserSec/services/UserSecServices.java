package com.aleandro.financial.UserSec.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.aleandro.financial.User.infra.UserDto;
import com.aleandro.financial.UserSec.infra.models.UserSecModel;
import com.aleandro.financial.UserSec.repositories.UserSecRepository;

@Service
public class UserSecServices implements UserDetailsService {
	
	@Autowired
	UserSecRepository user_sec_repository;

	public UserSecServices() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public UserSecServices(UserSecRepository user_sec_repository) {
		this.user_sec_repository = user_sec_repository;
	}
	
	public UserDto findById(Long id) {
		UserSecModel user = user_sec_repository.findById(id).orElseThrow();		
		return null;
		
	}

	@Override
	public UserSecModel loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserSecModel> user = user_sec_repository.findByUsername(username);
		if (user.isPresent()) {
			UserSecModel user_data = user.get();
			return user_data;
		} else {
			throw new UsernameNotFoundException(username);
		}
	}
	
}
