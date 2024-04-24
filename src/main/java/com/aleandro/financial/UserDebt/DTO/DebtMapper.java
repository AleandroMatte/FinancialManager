package com.aleandro.financial.UserDebt.DTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aleandro.financial.User.Repository.UserRepository;
import com.aleandro.financial.User.models.User;
import com.aleandro.financial.UserDebt.model.Debt;
import com.aleandro.financial.UserDebt.model.TypeDebt;
import com.aleandro.financial.UserDebt.repository.TypeDebtRepository;
import com.github.dozermapper.core.MappingException;

@Component
public class DebtMapper {
	
	@Autowired
	private UserRepository user_repo;
	@Autowired
	private TypeDebtRepository type_debt_repo;
	
	public DebtMapper() {
	}
	





	public List<DebtDto> ParseListDebtsToVo(List<Debt> list_dividas){
		List<DebtDto> lista_vo_objects = new ArrayList<>();
		for (Debt dividas : list_dividas) {
			lista_vo_objects.add(ParseDebtToVo(dividas));
		}
		return lista_vo_objects;
	}

	public  DebtDto ParseDebtToVo(Debt origin_object) {
		DebtDto novo_Vo = new DebtDto();
		novo_Vo.setId(origin_object.getId());
		novo_Vo.setPaga(origin_object.getPaga());
		novo_Vo.setDestino(origin_object.getDestino());
		novo_Vo.setUpdated_at(origin_object.getUpdated_at());
		novo_Vo.setCreated_at(origin_object.getCreated_at());
		novo_Vo.setValor(origin_object.getValor());
		novo_Vo.setData_pagamento(origin_object.getData_pagamento());
		
		if(origin_object.getUser_id().getId() == null) {
			throw new MappingException("Debts must be assigned to a user");
		}
		Long user_id = origin_object.getUser_id().getId();
		novo_Vo.setUser_id(user_id);
		novo_Vo.setRecorrencia_id(origin_object.getRecorrencia().getId());
		return novo_Vo;
	}
	
	
	public  Debt ParseVoToDebtEntity(DebtDto origin_object) {
		Debt novo_Vo = new Debt();
		System.out.println(origin_object.toString());
		novo_Vo.setDestino(origin_object.getDestino());
		novo_Vo.setPaga(origin_object.getPaga());
		novo_Vo.setData_pagamento(origin_object.getData_pagamento());
		novo_Vo.setValor(origin_object.getValor());
		if(origin_object.getUser_id() == null) {
			throw new MappingException("Debts must be assigned to a user");
		}
		Optional<User> user = user_repo.findById(origin_object.getUser_id());
		if(user.isPresent()) {
			novo_Vo.setUser_id(user.get());
		}
		
		if(origin_object.getRecorrencia_id() == null) {
			throw new MappingException("Debts must have a type");
		}
		Optional<TypeDebt> tipo_divida = type_debt_repo.findById(origin_object.getRecorrencia_id());
		if(tipo_divida.isPresent()) {
			novo_Vo.setRecorrencia(tipo_divida.get());
			
		}
		
		return novo_Vo;
	}

	

	
	
	

	
}
