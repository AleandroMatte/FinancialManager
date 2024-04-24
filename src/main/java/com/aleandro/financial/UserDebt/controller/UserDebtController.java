package com.aleandro.financial.UserDebt.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.aleandro.financial.UserDebt.DTO.DebtDto;
import com.aleandro.financial.UserDebt.service.DebtService;
import com.aleandro.financial.exceptions.DataNotFoundException;


@RestController
@RequestMapping("/user/{user_id}/debt")
public class UserDebtController {

	
	public UserDebtController() {
		// TODO Auto-generated constructor stub
	}
	

	@Autowired
	private DebtService debt_service;
	
	
	@CrossOrigin(methods = {RequestMethod.GET})
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/{debt_id}")
	public ResponseEntity<?> getDebtById(@PathVariable Long user_id, @PathVariable Long debt_id) {
		DebtDto debt = debt_service.get_debt_by_id(user_id,debt_id);
		return ResponseEntity.ok(debt);
	}
	
	@CrossOrigin(methods = {RequestMethod.GET})
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAllUserDebts(@PathVariable Long user_id) {
		List<DebtDto> user_debts_with_added_links = debt_service.get_user_debts(user_id);
		return ResponseEntity.ok(user_debts_with_added_links);
	}
	
	@CrossOrigin(methods = {RequestMethod.POST})
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
	
	@CrossOrigin(methods = {RequestMethod.DELETE})
	@DeleteMapping("/{debt_id}")
	public ResponseEntity<?> deleteDebtById(@PathVariable Long user_id, @PathVariable Long debt_id) {
		debt_service.delete_debt_by_id(user_id,debt_id);
		
		return ResponseEntity.status(201).build();
	}
	
	@CrossOrigin(methods = {RequestMethod.PUT})
	@PutMapping( consumes = MediaType.APPLICATION_JSON_VALUE, path = "{debt_id}")
	public ResponseEntity<?> updateDebt(@PathVariable Long user_id,
										   @PathVariable Long debt_id,
										   @RequestBody DebtDto debt_data) {
		debt_data.setUser_id(user_id);
		debt_data.setId(debt_id);
		System.out.println(debt_data);
		debt_service.update_user_debts(debt_data);
		return ResponseEntity.ok().build();
	}
	
	

	

	

}
