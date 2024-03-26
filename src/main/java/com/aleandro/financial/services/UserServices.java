package com.aleandro.financial.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aleandro.financial.DTO.DozerMapper;
import com.aleandro.financial.DTO.UserDto;
import com.aleandro.financial.Repository.UserRepository;
import com.aleandro.financial.models.User;

@Service
public class UserServices {
	
	private Logger logger = Logger.getLogger(UserServices.class.getName());
	
	@Autowired
	private UserRepository user_repository;

	public UserServices() {

	}
	
	public UserDto findById(Long id) {
		logger.info("finding_by_id");
		User user = user_repository.findById(id).orElseThrow();
		UserDto parsed_user = DozerMapper.parseObject(user, UserDto.class);
		return parsed_user;
		
	}
	
	public List<UserDto> get_all_users() {
		logger.info("finding_all_users");
		return DozerMapper.parseListObjects(user_repository.findAll(), UserDto.class);
	
	}
	
	public void create_user(UserDto user)  {
		logger.info("creating user");
		user_repository.save(DozerMapper.parseObject(user, User.class));
		logger.info("User "+ user.getName()+ " created");
	}
	
	public List<UserDto> create_many_users(List<UserDto> users)  {
		logger.info("creating user");
		List<User> user_entities = DozerMapper.parseListObjects(users, User.class);
		user_repository.saveAll(user_entities);
		return users;

	}
	
	public void delete_user(Long id)  {
		logger.info("deleting user");
		user_repository.deleteById(id);
	}

	
}
