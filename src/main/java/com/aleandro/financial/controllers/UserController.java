package com.aleandro.financial.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aleandro.financial.models.User;
import com.aleandro.financial.services.UserServices;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserServices user_service;

	public UserController() {
		// TODO Auto-generated constructor stub
	}
	
	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public User find_user_by_id(@PathVariable String id) {
		User usuario = user_service.findById(id);
		return usuario;
	}
	
	@GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<User> get_all_users() {
		return user_service.Mock_user_list(19);
			
		}
	@PostMapping(path = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void add_user(@RequestBody  User user) {
		user_service.create_user(user);
		
	}
		
	}


