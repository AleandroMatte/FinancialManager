package com.aleandro.financial.UserWin.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.text.DateFormatSymbols;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aleandro.financial.UserDebt.controller.UserDebtController;
import com.aleandro.financial.UserDebt.infra.DebtDto;
import com.aleandro.financial.UserDebt.model.Debt;
import com.aleandro.financial.UserDebt.model.TypeDebt;
import com.aleandro.financial.UserSec.infra.models.UserSecModel;
import com.aleandro.financial.UserSec.repositories.UserSecRepository;
import com.aleandro.financial.UserWin.Model.TypeWinning;
import com.aleandro.financial.UserWin.Model.Winnings;
import com.aleandro.financial.UserWin.infra.WinAnalyticsData;
import com.aleandro.financial.UserWin.infra.WinMapper;
import com.aleandro.financial.UserWin.infra.WinningsDto;
import com.aleandro.financial.UserWin.repository.TypeWinRepository;
import com.aleandro.financial.UserWin.repository.WinningRepository;
import com.aleandro.financial.exceptions.DataNotFoundException;
import com.aleandro.financial.exceptions.DebtAlreadyPaidException;

@Service
public class WinService {

	public WinService() {
		// TODO Auto-generated constructor stub
	}

	@Autowired
	private WinningRepository win_repository;
	@Autowired
	private TypeWinRepository type_win_repository;
	@Autowired
	private UserSecRepository user_repository;
	@Autowired
	private WinMapper win_mapper;

	public void post_win(WinningsDto data, Long repetitions) throws DataNotFoundException {
		Optional<UserSecModel> user = user_repository.findById(data.getUser_id());
		if (user.isEmpty()) {
			throw new DataNotFoundException("User not found!");
		}
		Optional<TypeWinning> type_win = type_win_repository.findById(data.getRecorrencia());
		if (type_win.isEmpty()) {
			throw new DataNotFoundException("User not found!");
		}
		if (repetitions == 1) {
			Winnings win = win_mapper.fromDto(data);
			win_repository.save(win);
			return;
		}
		insert_wins_repetitions(data, type_win.get(), repetitions);
	}

	private void insert_wins_repetitions(WinningsDto data, TypeWinning typeWinning, Long repetitions) {
		Date data_inicial = data.getData_recebimento();
		ArrayList<WinningsDto> wins = new ArrayList<>();
		wins.add(data);
		for (int i = 0; i < repetitions - 1; i++) {
			if (typeWinning.getRecorrencia().equals("Unico")) {
				break;
			}
			if (typeWinning.getRecorrencia().equals("Anual")) {
				data_inicial = DateUtils.addYears(data_inicial, 1);
			}
			if (typeWinning.getRecorrencia().equals("Semanal")) {
				data_inicial = DateUtils.addWeeks(data_inicial, 1);
			}
			if (typeWinning.getRecorrencia().equals("Mensal")) {
				data_inicial = DateUtils.addMonths(data_inicial, 1);
			}
			WinningsDto next_win_data = new WinningsDto(data);
			next_win_data.setData_recebimento(data_inicial);
			wins.add(next_win_data);
		}

		for (WinningsDto wintDto : wins) {
			win_repository.save(win_mapper.fromDto(wintDto));
		}

		return;

	}

	public WinningsDto get_win_by_id(Long user_id, Long debt_id) {
		Optional<Winnings> win = win_repository.CustomfindByUser_idAndWin_id(user_id, debt_id);

		if (win.isEmpty()) {
			throw new DataNotFoundException("debt not found!");
		}
		WinningsDto win_vo = win_mapper.toDto(win.get());
		WinningsDto wins_with_link = addSelfLinks(win_vo);
		return wins_with_link;
	}

	public HashMap<String, Object> get_user_wins(Long user_id) {
		List<Winnings> user_wins = win_repository.CustomfindByUser_id(user_id);
		List<WinningsDto> user_wins_vo = new ArrayList<>();
		for (Winnings winnings : user_wins) {
			user_wins_vo.add(win_mapper.toDto(winnings));
		}
		List<WinningsDto> user_wins_with_links = new ArrayList<>();
		user_wins_vo.forEach(x -> user_wins_with_links.add(addSelfLinks(x)));
		HashMap<String, Object> complete_win_data = calculate_wins_properties(user_wins_with_links);

		return complete_win_data;
	}

	public void update_user_win(WinningsDto new_win_data) {
		Optional<Winnings> win = win_repository.CustomfindByUser_idAndWin_id(new_win_data.getUser_id(),
				new_win_data.getId());
		if (win.isEmpty()) {
			throw new DataNotFoundException("debt not found!");
		}
		win_repository.UpdateWinByDto(new_win_data.getId(), new_win_data.getValor(), new_win_data.getOrigem(),
				new_win_data.getUser_id(), new_win_data.getData_recebimento(), new_win_data.getUser_id(),
				new_win_data.getRecebida());
	}

	public void delete_debt_by_id(Long user_id, Long debt_id) {
		win_repository.CustomDeleteByIds(user_id, debt_id);

	}

	private WinningsDto addSelfLinks(WinningsDto win) {
		win = (WinningsDto) win
				.add(linkTo(methodOn(UserDebtController.class).getDebtById(win.getUser_id(), win.getId())).withSelfRel()
						.withName("Actions"));
		return win;
	}

	private HashMap<String, Object> calculate_wins_properties(List<WinningsDto> wins) {
		HashMap<String, Object> win_properties = new HashMap<>();
		Double total_sum = 0D;
		Double quantidade_a_receber_este_ano = 0d;
		Long quantidade_recebidas_este_ano = 0L;
		Long recebimentos_não_feitos = 0L;
		Date todays_date = new Date();
		for (WinningsDto winningDto : wins) {
			Date win_date = winningDto.getData_recebimento();
			if (todays_date.getYear() == win_date.getYear() && winningDto.getRecebida()) {
				quantidade_recebidas_este_ano += 1;
				total_sum += winningDto.getValor();
			}
			if (todays_date.getYear() == win_date.getYear() && !winningDto.getRecebida()) {
				quantidade_a_receber_este_ano += winningDto.getValor();
			}

			if (todays_date.getYear() == win_date.getYear() && win_date.before(todays_date)
					&& !winningDto.getRecebida()) {
				recebimentos_não_feitos += 1;

			}

		}
		win_properties.put("win_data", wins);
		win_properties.put("total_amount_recieved", String.format("%.2f", total_sum));
		win_properties.put("num_of_wins_to_recieve", String.format("%.2f", quantidade_a_receber_este_ano));
		win_properties.put("num_of_wins_recieved", quantidade_recebidas_este_ano);
		win_properties.put("recebimentos_não_feitos", recebimentos_não_feitos);
		return win_properties;

	}

	public List<TypeWinning> get_win_types() {
		List<TypeWinning> lista = type_win_repository.findAll();
		return lista;
	}

	public void pay_win(Long user_id, Long win_id) {
		Optional<Winnings> win = win_repository.CustomfindByUser_idAndWin_id(user_id, win_id);
		if (win.isEmpty()) {
			throw new DataNotFoundException("win not found!");
		}
		Winnings win_vo = win.get();
		if (win_vo.getRecebida()) {
			throw new DebtAlreadyPaidException();
		}
		win_vo.setRecebida(true);
		win_repository.save(win_vo);
	}

	public void update_user_win(WinningsDto win_data, Long user_id_that_requested, String win_id) {
		Optional<Winnings> win_to_update = win_repository.findById(Long.valueOf(win_id));
		if (win_to_update.isEmpty()) {
			throw new DataNotFoundException("debt with this id doesn't exist");
		}
		Winnings original_win = win_to_update.get();
		if (win_data.getRecebida() != null) {
			original_win.setRecebida(win_data.getRecebida());
		}
		if (win_data.getData_recebimento() != null) {
			original_win.setData_recebimento(win_data.getData_recebimento());
		}

		if (win_data.getOrigem() != null && !win_data.getOrigem().strip().equals("")) {
			original_win.setOrigem(win_data.getOrigem());
		}
		if (win_data.getValor() != null && win_data.getValor() != 0d && win_data.getValor() != 0F) {
			original_win.setValor(win_data.getValor());
		}

		original_win.setId(Long.valueOf(win_id));
		win_repository.save(original_win);

	}

	public HashMap<String, Object> get_data_filtered(Long user_id, Date period_start, Date period_end) {
		 List<Winnings> lista = win_repository.CustomFindByUserIdFilteredByDates(user_id,period_start,period_end).get();
		 ArrayList<WinningsDto> lista_retornada = new ArrayList<>();
		 for (Winnings winnings : lista) {
			 
			lista_retornada.add(win_mapper.toDto(winnings));
		}
		 return get_data_filtered_by_month(lista_retornada);
		 
		
	}
	
	public HashMap<String, Object> get_data_filtered_by_month(ArrayList<WinningsDto> lista_retornada){
		HashMap<String, WinAnalyticsData> user_analytics = new HashMap<>();
		for (WinningsDto winnings : lista_retornada) {
			Date winning_date = winnings.getData_recebimento();
			@SuppressWarnings("deprecation")
			String month = new DateFormatSymbols().getMonths()[winning_date.getMonth()];
			if(user_analytics.containsKey(month)) {user_analytics.get(month).addAnaliticNode(winnings);}
			else {
					user_analytics.put(month,new WinAnalyticsData(winnings,month));
		}
			
		}
		HashMap<String, Object> user_analytics_serialized = new HashMap<>();
		for (String key : user_analytics.keySet()) {
			user_analytics_serialized.put(key, user_analytics.get(key).serialize());
		}
		
		
		return user_analytics_serialized;
		

}
	
}
