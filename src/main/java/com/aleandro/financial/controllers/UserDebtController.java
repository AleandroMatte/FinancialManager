package com.aleandro.financial.controllers;

import java.util.List;

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
import com.aleandro.financial.exceptions.DataNotFoundException;
import com.aleandro.financial.services.DebtService;


@RestController
@RequestMapping("/user/{user_id}/debt")
public class UserDebtController {

	
	public UserDebtController() {
		// TODO Auto-generated constructor stub
	}
	

	@Autowired
	private DebtService debt_service;
	
	@GetMapping("/{debt_id}")
	public ResponseEntity<?> getDebtById(@PathVariable Long user_id, @PathVariable Long debt_id) {
		return ResponseEntity.ok().build();
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAllUserDebts(@PathVariable Long user_id) {
		
		List<DebtDto> user_debts =  debt_service.get_user_debts(user_id);
		return ResponseEntity.ok(user_debts);
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> postUserDebt(@PathVariable Long user_id, @RequestBody DebtDto debt_data) {
		try {
			debt_data.setUser_id(user_id);
			debt_service.post_debt(debt_data);	
			
		} catch (DataNotFoundException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(e.getMessage());
		}
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
