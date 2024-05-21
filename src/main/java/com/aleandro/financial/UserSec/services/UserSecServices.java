package com.aleandro.financial.UserSec.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.aleandro.financial.User.infra.UserDto;
import com.aleandro.financial.UserSec.infra.models.UserSecModel;
import com.aleandro.financial.UserSec.repositories.UserSecRepository;
import com.aleandro.financial.exceptions.UserAlreadyExistsException;

@Service
public class UserSecServices implements UserDetailsService {
	
	@Autowired
	private UserSecRepository user_sec_repository;
	


	public UserSecServices() {
		// TODO Auto-generated constructor stub
	}

	
	public UserDto findById(Long id) {
		UserSecModel user = user_sec_repository.findById(id).orElseThrow();		
		return null;
		
	}
	public UserDto register_user(UserSecModel user) {
		user.setAccount_non_expired(true);
		user.setAccount_non_locked(true);
		user.setCredentials_non_expired(true);
		user.setEnabled(true);
		try {
			user_sec_repository.save(user);
		} catch (Exception e) {
			throw new UserAlreadyExistsException("User already exists, please change the username");
		}
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
