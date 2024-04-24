package com.aleandro.financial.User.controllers;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.aleandro.financial.User.infra.UserDto;
import com.aleandro.financial.User.services.UserServices;
@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserServices user_service;

	public UserController() {
		// TODO Auto-generated constructor stub
	}
	
	@CrossOrigin(methods = {RequestMethod.GET})
	@GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE,
											MediaType.APPLICATION_XML_VALUE})
	public UserDto find_user_by_id(@PathVariable Long id) {
		return user_service.findById(id);

	}
	
	@CrossOrigin(methods = {RequestMethod.GET})
	@GetMapping(path = "", produces = {MediaType.APPLICATION_JSON_VALUE,
									   MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<List<UserDto>> get_all_users() {
		return ResponseEntity.ok(user_service.get_all_users());
			
		}
	
	@CrossOrigin(methods = {RequestMethod.POST})
	@PostMapping(path = "", consumes = {MediaType.APPLICATION_JSON_VALUE,
									    MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<?> add_user(@RequestBody  UserDto user) {
		user_service.create_user(user);
		return ResponseEntity.ok().build();
		
	}
	
	@CrossOrigin(methods = {RequestMethod.GET})
	@PostMapping(path = "/add_many", consumes = {MediaType.APPLICATION_JSON_VALUE,
												MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<List<UserDto>> add_many_users(@RequestBody List<UserDto> lista_dados_users){
		List<UserDto> created_users = new ArrayList<UserDto>();
		created_users = user_service.create_many_users(lista_dados_users);
		return ResponseEntity.ok(created_users);
	}
	
	@CrossOrigin(methods = {RequestMethod.DELETE})
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<?> remove_user_by_id(@PathVariable Long id) {
		user_service.delete_user(id);
		return ResponseEntity.noContent().build();
	}
		
	}


