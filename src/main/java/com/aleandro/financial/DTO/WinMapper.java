package com.aleandro.financial.DTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aleandro.financial.Repository.TypeWinRepository;
import com.aleandro.financial.Repository.UserRepository;
import com.aleandro.financial.models.TypeWinning;
import com.aleandro.financial.models.User;
import com.aleandro.financial.models.Winnings;
import com.github.dozermapper.core.MappingException;

@Component
public class WinMapper {

	public WinMapper() {
		// TODO Auto-generated constructor stub
	}

	@Autowired
	private UserRepository user_repo;
	@Autowired
	private TypeWinRepository type_win_repo;
	



	public List<WinningsDto> ParseListDebtsToVo(List<Winnings> lista_recebimentos){
		List<WinningsDto> lista_vo_objects = new ArrayList<>();
		for (Winnings recebimentos : lista_recebimentos) {
			lista_vo_objects.add(ParseWinToVo(recebimentos));
		}
		return lista_vo_objects;
	}

	public  WinningsDto ParseWinToVo(Winnings origin_object) {
		WinningsDto novo_Vo = new WinningsDto();
		novo_Vo.setId(origin_object.getId());
		novo_Vo.setRecebida(origin_object.getRecebida());
		novo_Vo.setOrigem(origin_object.getOrigem());
		novo_Vo.setUpdated_at(origin_object.getUpdated_at());
		novo_Vo.setCreated_at(origin_object.getCreated_at());
		novo_Vo.setValor(origin_object.getValor());
		novo_Vo.setData_recebimento(origin_object.getData_recebimento());
		
		if(origin_object.getUser_id().getId() == null) {
			throw new MappingException("Debts must be assigned to a user");
		}
		Long user_id = origin_object.getUser_id().getId();
		novo_Vo.setUser_id(user_id);
		novo_Vo.setRecorrencia(origin_object.getRecorrencia().getId());
		return novo_Vo;
	}
	
	
	public  Winnings ParseVoToWinEntity(WinningsDto origin_object) {
		Winnings novo_Vo = new Winnings();
		System.out.println(origin_object.toString());
		novo_Vo.setOrigem(origin_object.getOrigem());
		novo_Vo.setRecebida(origin_object.getRecebida());
		novo_Vo.setData_recebimento(origin_object.getData_recebimento());
		novo_Vo.setValor(origin_object.getValor());
		if(origin_object.getUser_id() == null) {
			throw new MappingException("Debts must be assigned to a user");
		}
		Optional<User> user = user_repo.findById(origin_object.getUser_id());
		if(user.isPresent()) {
			novo_Vo.setUser_id(user.get());
		}
		
		if(origin_object.getRecorrencia() == null) {
			throw new MappingException("Debts must have a type");
		}
		Optional<TypeWinning> tipo_win = type_win_repo.findById(origin_object.getRecorrencia());
		if(tipo_win.isPresent()) {
			novo_Vo.setRecorrencia(tipo_win.get());
			
		}
		return novo_Vo;
	}
	
}
