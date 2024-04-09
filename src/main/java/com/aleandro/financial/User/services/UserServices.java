package com.aleandro.financial.User.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aleandro.financial.User.Repository.UserRepository;
import com.aleandro.financial.User.controllers.UserController;
import com.aleandro.financial.User.infra.UserDto;
import com.aleandro.financial.User.models.User;
import com.aleandro.financial.shared.infra.DozerMapper;
import com.aleandro.financial.shared.service.BaseService;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Service
public class UserServices extends BaseService {
	

	@Autowired
	private UserRepository user_repository;

	public UserServices() {

	}
	
	public UserDto findById(Long id) {
		User user = user_repository.findById(id).orElseThrow();
		UserDto parsed_user = DozerMapper.parseObject(user, UserDto.class);
		
		return addSelfLinks(parsed_user);
		
	}
	
	public List<UserDto> get_all_users() {
		List<UserDto> user_list =  DozerMapper.parseListObjects(user_repository.findAll(), UserDto.class);
		List<UserDto> updatedUserList = new ArrayList<>();
		user_list.forEach(x -> updatedUserList.add(addSelfLinks(x)));
		return updatedUserList;
	
	}
	
	public void create_user(UserDto user)  {
		user_repository.save(DozerMapper.parseObject(user, User.class));
	}
	
	public List<UserDto> create_many_users(List<UserDto> users)  {
		List<User> user_entities = DozerMapper.parseListObjects(users, User.class);
		List<UserDto> users_created = DozerMapper.parseListObjects(user_repository.saveAll(user_entities), UserDto.class);
		List<UserDto> updatedUserList = new ArrayList<>();
		users_created.forEach(x -> updatedUserList.add(addSelfLinks(x)));
		return users_created;

	}
	
	public void delete_user(Long id)  {
		user_repository.deleteById(id);
	}
	
	
	private UserDto addSelfLinks(UserDto user) {
		user = (UserDto) user.add(linkTo(methodOn(UserController.class).find_user_by_id(user.getId())).withSelfRel().withName("Actions"));
		return user;
	}
	


	
}
