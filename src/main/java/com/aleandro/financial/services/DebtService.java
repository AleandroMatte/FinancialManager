package com.aleandro.financial.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.aleandro.financial.DTO.CustomMappers;
import com.aleandro.financial.DTO.DebtDto;
import com.aleandro.financial.models.Dividas;

public class DebtService extends BaseService {

	public DebtService() {
		// TODO Auto-generated constructor stub
	}
	@Autowired
	private static DebtRepository debt_repository;
	
	
	public Dividas findById(Long id) {
		logger.info("");
		Dividas divida_info = debt_repository.findById(id).orElseThrow();
		Dividas parsed_debt = CustomMappers.CustomParse(divida_info);
		return parsed_debt;
		
	}
	
	public List<DebtDto> get_all_user_debts() {
		logger.info("");
		List<DebtDto> debt_list = new ArrayList<>();
		for (Dividas divida : debt_repository.findAll()) {
			DebtDto divida_dto = CustomMappers.CustomParse(divida);
			debt_list.add(divida_dto);
		}
		return debt_list;
	
	}
	
	public void create_debt(DebtDto divida_data)  {
		logger.info("");
		debt_repository.save(CustomMappers.CustomParse(divida_data));
	}
	
	
	public void delete_user(Long id)  {
		logger.info("user");
		debt_repository.deleteById(id);
	}

}
