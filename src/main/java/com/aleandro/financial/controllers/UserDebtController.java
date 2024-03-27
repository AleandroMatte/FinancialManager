package com.aleandro.financial.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aleandro.financial.services.UserServices;


@RestController
@RequestMapping("/user/{user_id}/debt")
public class UserDebtController {

	
	public UserDebtController() {
		// TODO Auto-generated constructor stub
	}
	
	static UserServices user_service;
	
	@GetMapping("/{debt_id}")
	public ResponseEntity<?> getDebtById(@PathVariable Long user_id, @PathVariable Long debt_id) {
		return ResponseEntity.ok().build();
	}
	
	@GetMapping
	public ResponseEntity<?> getAllUserDebts(@PathVariable Long user_id) {
		return ResponseEntity.ok().build();
	}
	
	@PostMapping
	public ResponseEntity<?> postUserDebt(@PathVariable Long user_id) {
		return ResponseEntity.ok().build();
	}
	@DeleteMapping("/{debt_id}")
	public ResponseEntity<?> deleteDebtById(@PathVariable Long user_id) {
		return ResponseEntity.ok().build();
	}
	@PutMapping("/{debt_id}")
	public ResponseEntity<?> getMethodName(@PathVariable Long user_id) {
		return ResponseEntity.ok().build();
	}
	

	

}
