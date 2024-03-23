package com.aleandro.financial.services;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aleandro.financial.Repository.UserRepository;
import com.aleandro.financial.models.BaseModel;
import com.aleandro.financial.models.User;

@Service
public class UserServices {
	
	private final AtomicLong counter = new AtomicLong();
	private Logger logger = Logger.getLogger(UserServices.class.getName());
	
	@Autowired
	private UserRepository user_repository;

	public UserServices() {

	}
	
	public User findById(String id) {
		logger.info("finding_by_id");
		User user = new User();
		user.setEmail("ale@123");
		user.setName("aleMatte");
		user.setId(Integer.parseInt(id));
		logger.info(user.toString() + "  found");
		return user;
		
	}
	
	public List<User> get_all_users() {
		logger.info("finding_all_users");
		List<User> users =  (List<User>)user_repository.findAll();
		return users;
	}
	public void create_user(User user)  {
		logger.info("creating user");
		try {
			User created_user = user_repository.save(user);
		}catch (Exception e) {
			System.out.println("do fucking nothing");
		}
		logger.info("User "+ user.getName()+ " created");
	}

	
}
