package com.aleandro.financial.services;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import com.aleandro.financial.models.User;

@Service
public class UserServices {
	
	private final AtomicLong counter = new AtomicLong();
	private Logger logger = Logger.getLogger(UserServices.class.getName());
	

	public UserServices() {
		// TODO Auto-generated constructor stub
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
	public List<User> Mock_user_list(int num_mocks) {
		logger.info("finding_by_id");
		ArrayList<User> users = new ArrayList<>(); 
		
		for(int i = 0; i < num_mocks; i++) {
			User mock_user =new User();
			mock_user.setId(counter.incrementAndGet());
			mock_user.setName("aleandro" + counter.get());
			mock_user.setEmail("aleandromatte@" + counter.get());
			users.add(mock_user);
		}
		return users;
		
	}
	public void create_user(User user)  {
		logger.info("creating user");
		try {
		Thread.sleep(1000);
		}catch (Exception e) {
			System.out.println("do fucking nothing");
		}
		logger.info("User "+ user+ " created");
	}

	
}
