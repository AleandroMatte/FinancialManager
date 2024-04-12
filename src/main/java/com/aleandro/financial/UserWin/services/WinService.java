package com.aleandro.financial.UserWin.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aleandro.financial.User.Repository.UserRepository;
import com.aleandro.financial.User.models.User;
import com.aleandro.financial.UserDebt.controller.UserDebtController;
import com.aleandro.financial.UserWin.Model.TypeWinning;
import com.aleandro.financial.UserWin.Model.Winnings;
import com.aleandro.financial.UserWin.infra.WinMapper;
import com.aleandro.financial.UserWin.infra.WinningsDto;
import com.aleandro.financial.UserWin.repository.TypeWinRepository;
import com.aleandro.financial.UserWin.repository.WinningRepository;
import com.aleandro.financial.exceptions.DataNotFoundException;

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
		WinningsDto wins_with_link = addSelfLinks(win_vo);
		return wins_with_link;
	}
	
	public List<WinningsDto> get_user_wins(Long user_id){
		List<Winnings> user_wins = win_repository.CustomfindByUser_id(user_id);
		List<WinningsDto> user_wins_vo = win_mapper.ParseListDebtsToVo(user_wins);
		List<WinningsDto> user_wins_with_links = new ArrayList<>();
		user_wins_vo.forEach(x ->user_wins_with_links.add(addSelfLinks(x)));
		
		return user_wins_with_links;
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
	
	
	
	private WinningsDto addSelfLinks(WinningsDto win) {
		win = (WinningsDto) win.add(linkTo(methodOn(UserDebtController.class).getDebtById(win.getUser_id(),win.getId())).withSelfRel().withName("Actions"));
		return win;
	}
	

}
