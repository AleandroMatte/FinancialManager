package com.aleandro.financial.UserDebt.service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aleandro.financial.UserDebt.controller.UserDebtController;
import com.aleandro.financial.UserDebt.infra.DebtDto;
import com.aleandro.financial.UserDebt.infra.DebtMapper;
import com.aleandro.financial.UserDebt.model.Debt;
import com.aleandro.financial.UserDebt.model.TypeDebt;
import com.aleandro.financial.UserDebt.repository.DebtRepository;
import com.aleandro.financial.UserDebt.repository.TypeDebtRepository;
import com.aleandro.financial.UserSec.infra.models.UserSecModel;
import com.aleandro.financial.UserSec.repositories.UserSecRepository;
import com.aleandro.financial.UserWin.infra.WinMapper;
import com.aleandro.financial.UserWin.infra.WinningsDto;
import com.aleandro.financial.exceptions.DataNotFoundException;
import com.aleandro.financial.shared.service.BaseService;

@Service
public class DebtService extends BaseService {

	public DebtService() {
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	private  DebtRepository debt_repository;
	@Autowired
	private TypeDebtRepository type_debt_repository;
	@Autowired
	private UserSecRepository user_repository;
	@Autowired
	private DebtMapper debt_mapper;
	

	
	
	public void post_debt(DebtDto data, Long repetitions) throws DataNotFoundException{
		Optional<UserSecModel> user = user_repository.findById(data.getUser_id());
		if (user.isEmpty()) {
			throw new DataNotFoundException("User not found!");
		}
		Optional<TypeDebt> type_debt = type_debt_repository.findById(data.getRecorrencia_id());
		if (type_debt.isEmpty()) {
			throw new DataNotFoundException("type_debt not found!");
		}
		if(Long.valueOf(repetitions)==1) {
			Debt debt = debt_mapper.fromDto(data);
			System.out.println(debt.toString());
			debt_repository.save(debt);
			return;
		}
		insert_debt_repetitions(data,type_debt.get() ,repetitions);
	}
	
	private void insert_debt_repetitions(DebtDto data, TypeDebt typeDebt, Long repetitions) {
		Date data_inicial = data.getData_pagamento();
		ArrayList<DebtDto> debts = new ArrayList<>();
		debts.add(data);
		System.out.println(data_inicial);
		for(int i=0;i<repetitions-1;i++) {
			System.out.println(data_inicial);
			if(typeDebt.getTipoDivida().equals("Anual")) {	
				data_inicial = DateUtils.addYears(data_inicial, 1);
			}
			if(typeDebt.getTipoDivida().equals("Semanal")) {	
				data_inicial = DateUtils.addWeeks(data_inicial, 1);
			}
			if(typeDebt.getTipoDivida().equals("Mensal")) {	
				data_inicial = DateUtils.addMonths(data_inicial, 1);
			}
			DebtDto next_debt_data = new DebtDto(data);
			next_debt_data.setData_pagamento(data_inicial);
			debts.add(next_debt_data);
		}
			
		for (DebtDto debtDto : debts) {
			debt_repository.save(debt_mapper.fromDto(debtDto));
		}
		
		return ; 
		
		
	}


	public DebtDto get_debt_by_id(Long user_id, Long debt_id) {
		Optional<Debt> debt = debt_repository.CustomfindByUser_idAndDebt_id(user_id,debt_id);
		if (debt.isEmpty()) {throw new DataNotFoundException("debt not found!");}
		DebtDto debt_vo = debt_mapper.toDto(debt.get());
		DebtDto debt_with_self_links = addSelfLinks(debt_vo);
		return debt_with_self_links;
	}
	
	public HashMap<String, Object> get_user_debts(Long user_id){
		List<Debt> user_debts = debt_repository.CustomfindByUser_id(user_id);
		List<DebtDto> user_debts_dto = new ArrayList<>();
		for(Debt debtVo : user_debts) {
			user_debts_dto.add(debt_mapper.toDto(debtVo));
		}
	
		List<DebtDto> user_debts_with_link =  new ArrayList<>();
		user_debts_dto.forEach(x -> user_debts_with_link.add(addSelfLinks(x)));
		
		HashMap<String, Object> complete_debt_data = calculate_debt_properties(user_debts_dto);

		return complete_debt_data;
	}
	

	public void update_user_debts(DebtDto new_user_debt_data){
		Optional<Debt> debt = debt_repository.CustomfindByUser_idAndDebt_id(new_user_debt_data.getUser_id(),
				new_user_debt_data.getId());
		if (debt.isEmpty()) {
			throw new DataNotFoundException("debt not found!");
		}
		debt_repository.UpdateDebtByDto(new_user_debt_data.getId(),
										new_user_debt_data.getValor(),
										new_user_debt_data.getDestino(),
										new_user_debt_data.getUser_id(),
										new_user_debt_data.getData_pagamento(),
										new_user_debt_data.getUser_id(),
										new_user_debt_data.getPaga());
		}
		
	
	public void delete_debt_by_id(Long user_id,Long debt_id){
		debt_repository.CustomDeleteByIds(user_id,debt_id);
		
	}
	
	private DebtDto addSelfLinks(DebtDto debt) {
		debt = (DebtDto) debt.add(linkTo(methodOn(UserDebtController.class).getDebtById(debt.getUser_id(),debt.getId())).withSelfRel().withName("Actions"));
		return debt;
	}

	public List<TypeDebt> get_debt_types() {
		List <TypeDebt> lista= type_debt_repository.findAll();
		return lista;
	}
	
	
	public boolean check_if_date_is_same_year_but_bigger(Calendar date) {
		Calendar todays_date = Calendar.getInstance();
		todays_date.setTime(new Date());
		if(date.YEAR == todays_date.YEAR && date.MONTH >= todays_date.MONTH
				&& date.DAY_OF_YEAR >= todays_date.DAY_OF_YEAR ) {
			return true;
		}
		return false;
		
		
	}
	
	@SuppressWarnings("deprecation")
	private HashMap<String, Object> calculate_debt_properties(List<DebtDto> debts) {
		HashMap<String, Object> debt_properties = new HashMap<>();
		Double total_sum = 0D;
		Double quantidade_a_pagar_este_ano = 0D;
		Double quantidade_pagas_este_ano = 0d;
		Long dividas_nao_pagas = 0L;
		for (DebtDto debtDto : debts) {
			Date todays_date = new Date();
			Date debt_date = debtDto.getData_pagamento();
			if(todays_date.getYear() == debt_date.getYear() && !debtDto.getPaga()) {
				quantidade_a_pagar_este_ano+=debtDto.getValor();
		}
			if(todays_date.getYear() == debt_date.getYear()&& debtDto.getPaga()) {
				total_sum += debtDto.getValor();
			}
			if(todays_date.getYear() == debt_date.getYear() && debt_date.before(todays_date)) {
			    quantidade_pagas_este_ano += debtDto.getPaga() ? 1D : 0d;
			    dividas_nao_pagas += debtDto.getPaga() ? 0 : 1;
				
			}		
			
	}
		debt_properties.put("Debts", debts);
		debt_properties.put("to_pay_this_year", quantidade_a_pagar_este_ano);
		debt_properties.put("number_of_debts_paid", quantidade_pagas_este_ano);
		debt_properties.put("unpaid_debts", dividas_nao_pagas);
		debt_properties.put("total_amount_paid", total_sum);
		return debt_properties;
		
	}
	
}
	
	

		


