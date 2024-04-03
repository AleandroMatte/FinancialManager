package com.aleandro.financial.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aleandro.financial.DTO.WinMapper;
import com.aleandro.financial.DTO.WinningsDto;
import com.aleandro.financial.Repository.TypeWinRepository;
import com.aleandro.financial.Repository.UserRepository;
import com.aleandro.financial.Repository.WinningRepository;
import com.aleandro.financial.exceptions.DataNotFoundException;
import com.aleandro.financial.models.TypeWinning;
import com.aleandro.financial.models.User;
import com.aleandro.financial.models.Winnings;

@Service
public class WinService {

	public WinService() {
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	private  WinningRepository win_repository;
	@Autowired
	private TypeWinRepository type_win_repository;
	@Autowired
	private UserRepository user_repository;
	
	@Autowired
	private WinMapper win_mapper = new WinMapper();
	
	
	public void post_win(WinningsDto data) throws DataNotFoundException{
		Optional<User> user = user_repository.findById(data.getUser_id());
		if (user.isEmpty()) {
			throw new DataNotFoundException("User not found!");
		}
		Optional<TypeWinning> type_win = type_win_repository.findById(data.getRecorrencia());
		if (type_win.isEmpty()) {
			throw new DataNotFoundException("User not found!");
		}
		
		Winnings win = win_mapper.ParseVoToWinEntity(data);
		win_repository.save(win);
	}
	
	public WinningsDto get_win_by_id(Long user_id, Long debt_id) {
		Optional<Winnings> win = win_repository.CustomfindByUser_idAndDebt_id(user_id,debt_id);
		if (win.isEmpty()) {throw new DataNotFoundException("debt not found!");}
		WinningsDto win_vo = win_mapper.ParseWinToVo(win.get());
		return win_vo;
	}
	
	public List<WinningsDto> get_user_wins(Long user_id){
		List<Winnings> user_wins = win_repository.CustomfindByUser_id(user_id);
		List<WinningsDto> user_wins_vo = win_mapper.ParseListDebtsToVo(user_wins);
		
		return user_wins_vo;
	}
	
	public void update_user_win(WinningsDto new_win_data){
		Optional<Winnings> win = win_repository.CustomfindByUser_idAndDebt_id(new_win_data.getUser_id(),
				new_win_data.getId());
		if (win.isEmpty()) {
			throw new DataNotFoundException("debt not found!");
		}
		win_repository.UpdateWinByDto(new_win_data.getId(),
										new_win_data.getValor(),
										new_win_data.getOrigem(),
										new_win_data.getUser_id(),
										new_win_data.getData_recebimento(),
										new_win_data.getUser_id(),
										new_win_data.getRecebida());
								}
		
	
	public void delete_debt_by_id(Long user_id,Long debt_id){
		win_repository.CustomDeleteByIds(user_id,debt_id);
		
	}

}
