package com.aleandro.financial.UserSec.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.aleandro.financial.UserSec.infra.dto.UserSecDto;
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

	
	public UserSecDto findById(Long id) {
		UserSecModel user = user_sec_repository.findById(id).orElseThrow();		
		return null;
		
	}
	public UserSecModel register_user(UserSecModel user) {
		user.setAccount_non_expired(true);
		user.setAccount_non_locked(true);
		user.setCredentials_non_expired(true);
		user.setEnabled(true);
		UserSecModel return_user;
		try {
			UserSecModel saved_user =  user_sec_repository.save(user);
			return_user = saved_user;
		} catch (Exception e) {
			throw new UserAlreadyExistsException(e.getMessage());
		}
		
		return return_user;
		
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


	public List<UserSecDto> get_all_users() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
