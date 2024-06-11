package com.aleandro.financial.UserDebt.infra;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aleandro.financial.UserDebt.model.Debt;
import com.aleandro.financial.UserDebt.model.TypeDebt;
import com.aleandro.financial.UserDebt.repository.TypeDebtRepository;
import com.aleandro.financial.UserSec.infra.models.UserSecModel;
import com.aleandro.financial.UserSec.repositories.UserSecRepository;
import com.aleandro.financial.exceptions.DataNotFoundException;


@Component
public class DebtMapper {
	
	
	
	public DebtMapper() {
	}

	@Autowired
	private static UserSecRepository user_repo;
	@Autowired
	private static TypeDebtRepository type_debt_repo;
	
	public DebtDto toDto(Debt debt_Vo) {
		DebtDto dto = new DebtDto();
		dto.setId(debt_Vo.getId());
		dto.setData_pagamento(debt_Vo.getData_pagamento());
		dto.setDestino(debt_Vo.getDestino());
		dto.setPaga(debt_Vo.getPaga());
		dto.setRecorrencia_id(debt_Vo.getRecorrencia().getId());
		dto.setUpdated_at(debt_Vo.getUpdated_at());
		dto.setCreated_at(debt_Vo.getCreated_at());
		dto.setValor(debt_Vo.getValor());
		dto.setUser_id(debt_Vo.getUser_id().getId());
		return dto;
	}
	
	public Debt fromDto(DebtDto debt_Dto) throws DataNotFoundException {
		Debt debt = new Debt();
		debt.setId(debt_Dto.getId());
		debt.setData_pagamento(debt_Dto.getData_pagamento());
		debt.setDestino(debt_Dto.getDestino());
		debt.setPaga(debt_Dto.getPaga());
		debt.setUpdated_at(debt_Dto.getUpdated_at());
		debt.setCreated_at(debt_Dto.getCreated_at());
		debt.setValor(debt_Dto.getValor());
		Optional<UserSecModel> user= user_repo.findById(debt_Dto.getUser_id());
		if(user.isEmpty()) {throw new DataNotFoundException("User with given id doesn't exist");}
		debt.setUser_id(user.get());
		
		 Optional<TypeDebt> type_debt= type_debt_repo.findById(debt_Dto.getRecorrencia_id());
		 
		if(type_debt.isEmpty()) {throw new DataNotFoundException("Invalid DebtType");}
		debt.setRecorrencia(type_debt.get());
	
		return debt;
	}


}
