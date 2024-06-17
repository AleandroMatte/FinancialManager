package com.aleandro.financial.UserDebt.controller;

import java.util.HashMap;
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
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aleandro.financial.UserDebt.infra.DebtDto;
import com.aleandro.financial.UserDebt.model.TypeDebt;
import com.aleandro.financial.UserDebt.service.DebtService;
import com.aleandro.financial.exceptions.DataNotFoundException;


@RestController
@RequestMapping("/user/debt")
public class UserDebtController {

	
	public UserDebtController() {
		// TODO Auto-generated constructor stub
	}
	

	@Autowired
	private DebtService debt_service;
	
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/type_debt")
	public ResponseEntity<?> getDebtTypes( ) {
		List<TypeDebt> debt_types = debt_service.get_debt_types();
		return ResponseEntity.status(200).body(debt_types);
	}
	
	@CrossOrigin(methods = {RequestMethod.GET})
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/{debt_id}")
	public ResponseEntity<?> getDebtById( @RequestAttribute Long user_id_that_requested, @PathVariable Long debt_id) {
		DebtDto debt = debt_service.get_debt_by_id(user_id_that_requested,debt_id);
		return ResponseEntity.ok(debt);
	}
	
	@CrossOrigin(methods = {RequestMethod.GET})
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAllUserDebts(@RequestAttribute Long user_id_that_requested) {
		HashMap<String, Object> user_debts_data = debt_service.get_user_debts(user_id_that_requested);
		return ResponseEntity.ok(user_debts_data);
	}
	
	@CrossOrigin(methods = {RequestMethod.POST,RequestMethod.OPTIONS })
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> postUserDebt(@RequestAttribute Long user_id_that_requested, 
			@RequestBody DebtDto debt_data,
			@RequestParam(required = false, defaultValue = "1" , value="repetitions") String repetitions) {
		try {
			System.out.println(debt_data.toString());
			debt_data.setUser_id(user_id_that_requested);
			debt_service.post_debt(debt_data,Long.valueOf(repetitions));	
			
		} catch (DataNotFoundException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.status(200).build();
	}
	
	@CrossOrigin(methods = {RequestMethod.DELETE})
	@DeleteMapping("/{debt_id}")
	public ResponseEntity<?> deleteDebtById(@RequestAttribute Long user_id_that_requested, @PathVariable Long debt_id) {
		debt_service.delete_debt_by_id(user_id_that_requested,debt_id);
		
		return ResponseEntity.status(201).build();
	}
	
	@CrossOrigin(methods = {RequestMethod.PUT})
	@PutMapping( consumes = MediaType.APPLICATION_JSON_VALUE, path = "/{debt_id}")
	public ResponseEntity<?> updateDebt(@RequestAttribute Long user_id_that_requested,
										   @PathVariable Long debt_id,
										   @RequestBody DebtDto debt_data) {
		debt_data.setUser_id(user_id_that_requested);
		debt_data.setId(debt_id);
		System.out.println(debt_data);
		debt_service.update_user_debts(debt_data);
		return ResponseEntity.ok().build();
	}
	
	@CrossOrigin(methods = {RequestMethod.POST})
	@PostMapping( consumes = MediaType.APPLICATION_JSON_VALUE, path = "/{debt_id}")
	public ResponseEntity<?> payDebt(@RequestAttribute Long user_id_that_requested,
			@PathVariable Long debt_id) {;
			System.out.println(debt_id);
		debt_service.payDebt(user_id_that_requested,debt_id);
		return ResponseEntity.ok().build();
	}
	
	

	

	

}
