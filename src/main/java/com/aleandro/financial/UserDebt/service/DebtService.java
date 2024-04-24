package com.aleandro.financial.UserDebt.service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aleandro.financial.User.Repository.UserRepository;
import com.aleandro.financial.User.models.User;
import com.aleandro.financial.UserDebt.DTO.DebtDto;
import com.aleandro.financial.UserDebt.DTO.DebtMapper;
import com.aleandro.financial.UserDebt.controller.UserDebtController;
import com.aleandro.financial.UserDebt.model.Debt;
import com.aleandro.financial.UserDebt.model.TypeDebt;
import com.aleandro.financial.UserDebt.repository.DebtRepository;
import com.aleandro.financial.UserDebt.repository.TypeDebtRepository;
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
	private UserRepository user_repository;
	
	@Autowired
	private DebtMapper debt_mapper = new DebtMapper();
	
	
	public void post_debt(DebtDto data) throws DataNotFoundException{
		Optional<User> user = user_repository.findById(data.getUser_id());
		if (user.isEmpty()) {
			throw new DataNotFoundException("User not found!");
		}
		Optional<TypeDebt> type_debt = type_debt_repository.findById(data.getRecorrencia_id());
		if (type_debt.isEmpty()) {
			throw new DataNotFoundException("User not found!");
		}
		
		Debt debt = debt_mapper.ParseVoToDebtEntity(data);
		System.out.println(debt.toString());
		debt_repository.save(debt);
	}
	
	public DebtDto get_debt_by_id(Long user_id, Long debt_id) {
		Optional<Debt> debt = debt_repository.CustomfindByUser_idAndDebt_id(user_id,debt_id);
		if (debt.isEmpty()) {throw new DataNotFoundException("debt not found!");}
		DebtDto debt_vo = debt_mapper.ParseDebtToVo(debt.get());
		DebtDto debt_with_self_links = addSelfLinks(debt_vo);
		return debt_with_self_links;
	}
	
	public List<DebtDto> get_user_debts(Long user_id){
		List<Debt> user_debts = debt_repository.CustomfindByUser_id(user_id);
		
		List<DebtDto> user_debts_vo = debt_mapper.ParseListDebtsToVo(user_debts);
		List<DebtDto> user_debts_with_link =  new ArrayList<>();
		user_debts_vo.forEach(x -> user_debts_with_link.add(addSelfLinks(x)));
		
		return user_debts_with_link;
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
	
	
}
	
	

		


