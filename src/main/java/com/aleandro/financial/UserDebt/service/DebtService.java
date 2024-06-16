package com.aleandro.financial.UserDebt.service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aleandro.financial.UserDebt.controller.UserDebtController;
import com.aleandro.financial.UserDebt.infra.DebtDto;
import com.aleandro.financial.UserDebt.infra.DebtMapper;
import com.aleandro.financial.UserDebt.model.Debt;
import com.aleandro.financial.UserDebt.model.TypeDebt;
import com.aleandro.financial.UserDebt.repository.DebtRepository;
import com.aleandro.financial.UserDebt.repository.TypeDebtRepository;
import com.aleandro.financial.UserSec.infra.models.UserSecModel;
import com.aleandro.financial.UserSec.repositories.UserSecRepository;
import com.aleandro.financial.exceptions.DataNotFoundException;
import com.aleandro.financial.shared.service.BaseService;

@Service
public class DebtService extends BaseService {

	public DebtService() {
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	private  DebtRepository debt_repository;
	@Autowired
	private TypeDebtRepository type_debt_repository;
	@Autowired
	private UserSecRepository user_repository;
	@Autowired
	private DebtMapper debt_mapper;
	

	
	
	public void post_debt(DebtDto data) throws DataNotFoundException{
		Optional<UserSecModel> user = user_repository.findById(data.getUser_id());
		if (user.isEmpty()) {
			throw new DataNotFoundException("User not found!");
		}
		Optional<TypeDebt> type_debt = type_debt_repository.findById(data.getRecorrencia_id());
		if (type_debt.isEmpty()) {
			throw new DataNotFoundException("User not found!");
		}
		
		Debt debt = debt_mapper.fromDto(data);
		System.out.println(debt.toString());
		debt_repository.save(debt);
	}
	
	public DebtDto get_debt_by_id(Long user_id, Long debt_id) {
		Optional<Debt> debt = debt_repository.CustomfindByUser_idAndDebt_id(user_id,debt_id);
		if (debt.isEmpty()) {throw new DataNotFoundException("debt not found!");}
		DebtDto debt_vo = debt_mapper.toDto(debt.get());
		DebtDto debt_with_self_links = addSelfLinks(debt_vo);
		return debt_with_self_links;
	}
	
	public HashMap<String, Object> get_user_debts(Long user_id){
		List<Debt> user_debts = debt_repository.CustomfindByUser_id(user_id);
		List<DebtDto> user_debts_vo = new ArrayList<>();
		Calendar calendar = Calendar.getInstance();
		Long number_of_debts_to_pay_this_year = 0L;
		Long number_of_debts_paid = 0L;
		Long total_debts_non_paid_in_time = 0L;
		for (Debt debt : user_debts) {
			calendar.setTime(debt.getData_pagamento());
			if(check_if_date_is_same_year_but_bigger(calendar)) {
				number_of_debts_to_pay_this_year+=1;	
			}
			if(debt.getPaga() && check_if_date_is_same_year_but_bigger(calendar)) {number_of_debts_paid+=1;}
			if(!debt.getPaga() && check_if_date_is_same_year_but_bigger(calendar)) {
				total_debts_non_paid_in_time+=1;
			}
			DebtDto debt_converted = debt_mapper.toDto(debt);
			user_debts_vo.add(debt_converted);
		}
		List<DebtDto> user_debts_with_link =  new ArrayList<>();
		user_debts_vo.forEach(x -> user_debts_with_link.add(addSelfLinks(x)));
		
		HashMap<String, Object> results = new HashMap<>();
		results.put("Debts", user_debts_with_link);
		results.put("to_pay_this_year", number_of_debts_to_pay_this_year);
		results.put("number_of_debts_paid", number_of_debts_paid);
		results.put("unpaid_debts", total_debts_non_paid_in_time);
		return results;
	}
	
	public void update_user_debts(DebtDto new_user_debt_data){
		Optional<Debt> debt = debt_repository.CustomfindByUser_idAndDebt_id(new_user_debt_data.getUser_id(),
				new_user_debt_data.getId());
		if (debt.isEmpty()) {
			throw new DataNotFoundException("debt not found!");
		}
		debt_repository.UpdateDebtByDto(new_user_debt_data.getId(),
										new_user_debt_data.getValor(),
										new_user_debt_data.getDestino(),
										new_user_debt_data.getUser_id(),
										new_user_debt_data.getData_pagamento(),
										new_user_debt_data.getUser_id(),
										new_user_debt_data.getPaga());
		}
		
	
	public void delete_debt_by_id(Long user_id,Long debt_id){
		debt_repository.CustomDeleteByIds(user_id,debt_id);
		
	}
	
	private DebtDto addSelfLinks(DebtDto debt) {
		debt = (DebtDto) debt.add(linkTo(methodOn(UserDebtController.class).getDebtById(debt.getUser_id(),debt.getId())).withSelfRel().withName("Actions"));
		return debt;
	}

	public List<TypeDebt> get_debt_types() {
		List <TypeDebt> lista= type_debt_repository.findAll();
		return lista;
	}
	
	
	public boolean check_if_date_is_same_year_but_bigger(Calendar date) {
		Calendar todays_date = Calendar.getInstance();
		todays_date.setTime(new Date());
		if(date.YEAR == todays_date.YEAR && date.MONTH >= todays_date.MONTH
				&& date.DAY_OF_YEAR >= todays_date.DAY_OF_YEAR ) {
			return true;
		}
		return false;
		
		
	}
	
	
}
	
	

		


