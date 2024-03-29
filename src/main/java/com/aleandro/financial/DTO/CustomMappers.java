package com.aleandro.financial.DTO;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.aleandro.financial.Repository.DebtRepository;
import com.aleandro.financial.Repository.TypeDebtRepository;
import com.aleandro.financial.Repository.UserRepository;
import com.aleandro.financial.models.Dividas;
import com.aleandro.financial.models.Recebimentos;
import com.aleandro.financial.models.TipoDividas;
import com.aleandro.financial.models.User;
import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import com.github.dozermapper.core.MappingException;

public class CustomMappers {
	
	@Autowired
	private static UserRepository user_repo;
	private static TypeDebtRepository type_debt_repo;
	
	
	public static DebtDto ParseDebtToVo(Dividas origin_object) {
		DebtDto novo_Vo = new DebtDto();
		novo_Vo.setId(origin_object.getId());
		novo_Vo.setPaga(origin_object.getPaga());
		novo_Vo.setDestino(origin_object.getDestino());
		novo_Vo.setUpdated_at(origin_object.getUpdated_at());
		novo_Vo.setCreated_at(origin_object.getCreated_at());
		
		if(origin_object.getUser_id().getId() == null) {
			throw new MappingException("Debts must be assigned to a user");
		}
		Long user_id = origin_object.getUser_id().getId();
		Optional<User> user_entity = user_repo.findById(user_id);
		novo_Vo.setUser_id(user_entity.get().getId());
		
		if(origin_object.getRecorrencia().getId() == null) {
			throw new MappingException("Debts must have a type");
		}
		Optional<TipoDividas> recorrencia = type_debt_repo.findById(origin_object.getRecorrencia().getId());
		novo_Vo.setRecorrencia(recorrencia.get());
		return novo_Vo;
	}
	
	
	public static Dividas ParseVoToDebtEntity(DebtDto origin_object) {
		Dividas novo_Vo = new Dividas();
		novo_Vo.setDestino(origin_object.getDestino());
		novo_Vo.setUpdated_at(origin_object.getUpdated_at());
		novo_Vo.setCreated_at(origin_object.getCreated_at());
		novo_Vo.setPaga(origin_object.getPaga());
		
		
		novo_Vo.setId(origin_object.getId());
		if(origin_object.getUser_id() == null) {
			throw new MappingException("Debts must be assigned to a user");
		}
		Optional<User> user = user_repo.findById(origin_object.getUser_id());
		if(user.isPresent()) {
			novo_Vo.setUser_id(user.get());
		}
		
		if(origin_object.getRecorrencia().getId() == null) {
			throw new MappingException("Debts must have a type");
		}
		Optional<TipoDividas> tipo_divida = type_debt_repo.findById(origin_object.getId());
		if(tipo_divida.isPresent()) {
			novo_Vo.setRecorrencia(tipo_divida.get());
			
		}
		
		return novo_Vo;
	}
	

	
	
	

	
}
