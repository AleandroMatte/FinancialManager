package com.aleandro.financial.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aleandro.financial.Repository.UserRepository;
import com.aleandro.financial.models.User;

@Service
public class UserServices {
	
	private Logger logger = Logger.getLogger(UserServices.class.getName());
	
	@Autowired
	private UserRepository user_repository;

	public UserServices() {

	}
	
	public User findById(Long id) {
		logger.info("finding_by_id");
		
		return user_repository.findById(id).orElseThrow();
		
	}
	
	public List<User> get_all_users() {
		logger.info("finding_all_users");
		List<User> users =  (List<User>)user_repository.findAll();
		return users;
	}
	
	public void create_user(User user)  {
		logger.info("creating user");
		user_repository.save(user);
		logger.info("User "+ user.getName()+ " created");
	}
	
	public List<User> create_many_users(List<User> users)  {
		logger.info("creating user");
		List<User> created_users = user_repository.saveAll(users);
		return created_users;

	}
	
	public void delete_user(Long id)  {
		logger.info("deleting user");
		user_repository.deleteById(id);
	}

	
}
