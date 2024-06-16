package com.aleandro.financial.UserWin.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aleandro.financial.UserDebt.controller.UserDebtController;
import com.aleandro.financial.UserDebt.model.TypeDebt;
import com.aleandro.financial.UserSec.infra.models.UserSecModel;
import com.aleandro.financial.UserSec.repositories.UserSecRepository;
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
	private UserSecRepository user_repository;
	@Autowired
	private WinMapper win_mapper;
		
	
	public void post_win(WinningsDto data) throws DataNotFoundException{
		Optional<UserSecModel> user = user_repository.findById(data.getUser_id());
		if (user.isEmpty()) {
			throw new DataNotFoundException("User not found!");
		}
		Optional<TypeWinning> type_win = type_win_repository.findById(data.getRecorrencia());
		if (type_win.isEmpty()) {
			throw new DataNotFoundException("User not found!");
		}
		
		Winnings win = win_mapper.fromDto(data);
		win_repository.save(win);
	}
	
	public WinningsDto get_win_by_id(Long user_id, Long debt_id) {
		Optional<Winnings> win = win_repository.CustomfindByUser_idAndDebt_id(user_id,debt_id);
		
		
		if (win.isEmpty()) {throw new DataNotFoundException("debt not found!");}
		WinningsDto win_vo = win_mapper.toDto(win.get());
		WinningsDto wins_with_link = addSelfLinks(win_vo);
		return wins_with_link;
	}
	
	public HashMap<String, Object> get_user_wins(Long user_id){
		List<Winnings> user_wins = win_repository.CustomfindByUser_id(user_id);
		List<WinningsDto> user_wins_vo = new ArrayList<>();
		for (Winnings winnings : user_wins) {
			user_wins_vo.add(win_mapper.toDto(winnings));
		}
		List<WinningsDto> user_wins_with_links = new ArrayList<>();
		user_wins_vo.forEach(x ->user_wins_with_links.add(addSelfLinks(x)));
		HashMap<String, Object> complete_win_data = calculate_debts_properties(user_wins_with_links);
		
		return complete_win_data;
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
	
	private HashMap<String, Object> calculate_debts_properties(List<WinningsDto> wins) {
		HashMap<String, Object> win_properties = new HashMap<>();
		Double total_sum = 0D;
		Long quantidade_a_receber_este_ano = 0L;
		Long quantidade_recebidas_este_ano = 0L;
		Long recebimentos_não_feitos = 0L;
		Calendar todays_date = Calendar.getInstance();
		todays_date.setTime(new Date()); 
		for (WinningsDto winningDto : wins) {
			Calendar date = Calendar.getInstance();
			date.setTime(winningDto.getData_recebimento());
			if(date.YEAR == todays_date.YEAR && date.MONTH >= todays_date.MONTH
					&& date.DAY_OF_YEAR >= todays_date.DAY_OF_YEAR ) {
				total_sum += winningDto.getValor();
				quantidade_a_receber_este_ano+=1;
		}
			if(date.YEAR == todays_date.YEAR && date.MONTH <= todays_date.MONTH
					&& date.DAY_OF_YEAR <= todays_date.DAY_OF_YEAR) {
			    quantidade_recebidas_este_ano += winningDto.getRecebida() ? 1 : 0;
			    recebimentos_não_feitos += winningDto.getRecebida() ? 0 : 1;
				
			}		
	}
		win_properties.put("win_data", wins);
		win_properties.put("total_amount_recieved", total_sum);
		win_properties.put("num_of_wins_to_recieve", quantidade_a_receber_este_ano);
		win_properties.put("num_of_wins_recieved", quantidade_recebidas_este_ano);
		win_properties.put("recebimentos_não_feitos", recebimentos_não_feitos);
		return win_properties;
		
	}

	public List<TypeWinning> get_win_types() {
		List <TypeWinning> lista= type_win_repository.findAll();
		return lista;
	}

}
