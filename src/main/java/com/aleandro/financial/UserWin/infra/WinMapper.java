package com.aleandro.financial.UserWin.infra;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aleandro.financial.UserSec.infra.models.UserSecModel;
import com.aleandro.financial.UserSec.repositories.UserSecRepository;
import com.aleandro.financial.UserWin.Model.TypeWinning;
import com.aleandro.financial.UserWin.Model.Winnings;
import com.aleandro.financial.UserWin.repository.TypeWinRepository;
import com.aleandro.financial.exceptions.DataNotFoundException;

@Component
public class WinMapper {
	
	
		public WinMapper() {}

		@Autowired
		private  UserSecRepository user_repo;
		@Autowired
		private TypeWinRepository type_win_repo;
		
		public WinningsDto toDto(Winnings win_vo) throws DataNotFoundException{
			WinningsDto dto = new WinningsDto();
			dto.setId(win_vo.getId());
			dto.setData_recebimento(win_vo.getData_recebimento());
			dto.setOrigem(win_vo.getOrigem());
			dto.setRecebida(win_vo.getRecebida());
			dto.setRecorrencia(win_vo.getRecorrencia().getId());
			dto.setUpdated_at(win_vo.getUpdated_at());
			dto.setCreated_at(win_vo.getCreated_at());
			dto.setValor(win_vo.getValor());
			dto.setUser_id(win_vo.getUser().getId());
			return dto;
		}
		
		public Winnings fromDto(WinningsDto win_dto) throws DataNotFoundException {
			Winnings win = new Winnings();
			win.setId(win_dto.getId());
			win.setData_recebimento(win_dto.getData_recebimento());
			win.setOrigem(win_dto.getOrigem());
			win.setRecebida(win_dto.getRecebida());
			win.setUpdated_at(win_dto.getUpdated_at());
			win.setCreated_at(win_dto.getCreated_at());
			win.setValor(win_dto.getValor());
			Optional<UserSecModel> user= user_repo.findById(win_dto.getUser_id());
			if(user.isEmpty()) {throw new DataNotFoundException("User with given id doesn't exist");}
			win.setUser(user.get());	
			 Optional<TypeWinning> type_debt= type_win_repo.findById(win_dto.getRecorrencia());
			if(type_debt.isEmpty()) {throw new DataNotFoundException("Invalid DebtType");}
			win.setRecorrencia(type_debt.get());
		
			return win;
		}


	}



