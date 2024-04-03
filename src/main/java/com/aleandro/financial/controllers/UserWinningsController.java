package com.aleandro.financial.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aleandro.financial.DTO.DebtDto;
import com.aleandro.financial.services.DebtService;

@RestController
@RequestMapping("/user/{user_id}/wins")
public class UserWinningsController {

	public UserWinningsController() {
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	private DebtService debt_service;
	
	@GetMapping("/{win_id}")
	public ResponseEntity<?> getWinById(@PathVariable Long user_id, @PathVariable Long win_id) {

		return ResponseEntity.ok().build();
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAllUserWins(@PathVariable Long user_id) {
		

		return ResponseEntity.ok().build();
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public <T> ResponseEntity<?> postUserWin(@PathVariable Long user_id, @RequestBody T debt_data) {

		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/{win_id}")
	public ResponseEntity<?> deleteWinById(@PathVariable Long user_id, @PathVariable Long debt_id) {
		
		return ResponseEntity.status(201).build();
	}
	
	@PutMapping("{win_id}")
	public ResponseEntity<?>  updateWin(@PathVariable Long user_id,
										   @PathVariable Long debt_id,
										   @RequestBody DebtDto debt_data) {
		debt_data.setUser_id(user_id);
		debt_data.setId(debt_id);
		System.out.println(debt_data);
		debt_service.update_user_debts(debt_data);
		return ResponseEntity.ok().build();
	}

}
