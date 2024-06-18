package com.aleandro.financial.UserWin.controller;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
import com.aleandro.financial.UserWin.Model.Winnings;
import com.aleandro.financial.UserWin.infra.WinAnalyticsData;
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
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE,path = "/analytics")
	public ResponseEntity<?> getUserWinningDataFiltered(
			@RequestParam(required = false, defaultValue = "Anual",name = "period_type") String period_type,
			@RequestParam(required = true,name = "period_start") @DateTimeFormat(pattern = "yyyy-MM-dd")
			Date period_start,
			@RequestParam(required = true,name = "period_end") @DateTimeFormat(pattern = "yyyy-MM-dd")
			Date period_end,
			@RequestAttribute String user_id_that_requested
			) {
		//ArrayList<HashMap<String,Set<Object> >> data_filtered = win_service.get_data_filtered(period_type,period_start,period_end);
		HashMap<String, Object> user_analytics= win_service.get_data_filtered(Long.valueOf(user_id_that_requested),period_start,period_end);
		return ResponseEntity.status(200).body(user_analytics);
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
		win_service.post_win(win_data,Long.valueOf(repetitions));
		return ResponseEntity.created(null).build();
	}
	@CrossOrigin(methods = {RequestMethod.POST})
	@PostMapping(path = "{win_id}" , consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public <T> ResponseEntity<?> payWin(@RequestAttribute Long user_id_that_requested,
			@PathVariable(name = "win_id") String win_id) {
		win_service.pay_win(user_id_that_requested, Long.valueOf(win_id));
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
										   @PathVariable String win_id,
										   @RequestBody WinningsDto win_data) {
		win_service.update_user_win(win_data, user_id_that_requested, win_id);

		return ResponseEntity.ok().build();
	}
	


}
