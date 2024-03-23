package com.aleandro.financial.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
	public User find_user_by_id(@PathVariable Long id) {
		return user_service.findById(id);

	}
	
	@GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<User> get_all_users() {
		return user_service.get_all_users();
			
		}
	@PostMapping(path = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void add_user(@RequestBody  User user) {
		user_service.create_user(user);
		
	}
	
	@PostMapping(path = "/add_many", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<User>> add_many_users(@RequestBody List<User> lista_dados_users){
		List<User> created_users = new ArrayList<User>();
		created_users = user_service.create_many_users(lista_dados_users);
		return ResponseEntity.ok(created_users);
	}
	
	@DeleteMapping(path = "/delete/{id}")
	public ResponseEntity<?> remove_user_by_id(@PathVariable Long id) {
		user_service.delete_user(id);
		return ResponseEntity.noContent().build();
	}
		
	}


