package com.aleandro.financial.UserWin.controller;


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

import com.aleandro.financial.UserDebt.model.TypeDebt;
import com.aleandro.financial.UserWin.Model.TypeWinning;
import com.aleandro.financial.UserWin.infra.WinningsDto;
import com.aleandro.financial.UserWin.services.WinService;

@RestController
@RequestMapping("/user/wins")
public class UserWinningsController {

	public UserWinningsController() {
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	private WinService win_service;
	
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/type_win")
	public ResponseEntity<?> getDebtTypes( ) {
		List<TypeWinning> win_types = win_service.get_win_types();
		return ResponseEntity.status(200).body(win_types);
	}
	
	@CrossOrigin(methods = {RequestMethod.GET})
	@GetMapping(path = "/{win_id}",
				produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<?> getWinById(@RequestAttribute Long user_id_that_requested, @PathVariable Long win_id) {
		WinningsDto win_data =  win_service.get_win_by_id(user_id_that_requested, win_id);
		return ResponseEntity.ok(win_data);
	}
	
	@CrossOrigin(methods = {RequestMethod.GET})
	@GetMapping(path = "",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<?> getAllUserWins(@RequestAttribute Long user_id_that_requested) {
		HashMap<String, Object> user_wins_data = win_service.get_user_wins(user_id_that_requested);
		return ResponseEntity.ok(user_wins_data);
	}
	
	@CrossOrigin(methods = {RequestMethod.POST})
	@PostMapping(path = "" , consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public <T> ResponseEntity<?> postUserWin(@RequestAttribute Long user_id_that_requested, 
			@RequestBody WinningsDto win_data,
			@RequestParam(required = false,defaultValue = "1" ,name = "repetitions") String repetitions) {
		win_data.setUser_id(user_id_that_requested);
		win_service.post_win(win_data);
		return ResponseEntity.created(null).build();
	}
	
	@CrossOrigin(methods = {RequestMethod.DELETE})
	@DeleteMapping("/{win_id}")
	public ResponseEntity<?> deleteWinById(@RequestAttribute Long user_id_that_requested, @PathVariable Long win_id) {
		win_service.delete_debt_by_id(user_id_that_requested, win_id);
		return ResponseEntity.status(201).build();
	}
	
	@CrossOrigin(methods = {RequestMethod.PUT})
	@PutMapping(path = "/{win_id}",
				produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE},
				consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<?>  updateWin(@RequestAttribute Long user_id_that_requested,
										   @PathVariable Long win_id,
										   @RequestBody WinningsDto win_data) {
		win_data.setId(win_id);
		win_data.setUser_id(user_id_that_requested);
		win_service.update_user_win(win_data);

		return ResponseEntity.ok().build();
	}
	


}
