package com.aleandro.financialtest.unittests.fixtures;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.aleandro.financial.UserDebt.model.Debt;
import com.aleandro.financial.UserDebt.model.TypeDebt;

public class DebtMocks {

	public static Debt mock_one_debt() {
		TypeDebt tipo_divida = new TypeDebt();
		tipo_divida.setId(1L);
		Debt debt = new Debt();
		debt.setUser_id(UserMocks.mock_one_user());
		debt.setData_pagamento(Date.from(Instant.EPOCH));
		debt.setDestino("Custom destini");
		debt.setValor(227474d);
		debt.setId(1L);
		debt.setRecorrencia(tipo_divida);
		return debt;
	}
	
	public static List<Debt> mock_many_debts(int num_debts){
		List<Debt> debts = new ArrayList<>();
		int rep_counter = 0;
		while (rep_counter<num_debts) {
			debts.add(mock_one_debt());
			rep_counter++;
		}
		return debts;
	}

}
