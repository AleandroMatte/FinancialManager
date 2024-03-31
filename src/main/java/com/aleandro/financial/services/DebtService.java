package com.aleandro.financial.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aleandro.financial.DTO.DebtMapper;
import com.aleandro.financial.DTO.DebtDto;
import com.aleandro.financial.Repository.DebtRepository;
import com.aleandro.financial.Repository.TypeDebtRepository;
import com.aleandro.financial.Repository.UserRepository;
import com.aleandro.financial.exceptions.DataNotFoundException;
import com.aleandro.financial.models.Dividas;
import com.aleandro.financial.models.TipoDividas;
import com.aleandro.financial.models.User;

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
		Optional<TipoDividas> type_debt = type_debt_repository.findById(data.getRecorrencia_id());
		if (type_debt.isEmpty()) {
			throw new DataNotFoundException("User not found!");
		}
		
		Dividas debt = debt_mapper.ParseVoToDebtEntity(data);
		System.out.println(debt.toString());
		debt_repository.save(debt);
		
	}
	
	

		

}
