package com.aleandro.financial.User.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aleandro.financial.User.Repository.UserRepository;
import com.aleandro.financial.User.infra.UserDto;
import com.aleandro.financial.User.models.User;
import com.aleandro.financial.shared.infra.DozerMapper;
import com.aleandro.financial.shared.service.BaseService;

@Service
public class UserServices extends BaseService {
	

	@Autowired
	private UserRepository user_repository;

	public UserServices() {

	}
	
	public UserDto findById(Long id) {
		User user = user_repository.findById(id).orElseThrow();
		UserDto parsed_user = DozerMapper.parseObject(user, UserDto.class);
		return parsed_user;
		
	}
	
	public List<UserDto> get_all_users() {
		return DozerMapper.parseListObjects(user_repository.findAll(), UserDto.class);
	
	}
	
	public void create_user(UserDto user)  {
		user_repository.save(DozerMapper.parseObject(user, User.class));
	}
	
	public List<UserDto> create_many_users(List<UserDto> users)  {
		List<User> user_entities = DozerMapper.parseListObjects(users, User.class);
		user_repository.saveAll(user_entities);
		return users;

	}
	
	public void delete_user(Long id)  {
		user_repository.deleteById(id);
	}

	
}
