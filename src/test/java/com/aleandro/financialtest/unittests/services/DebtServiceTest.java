package com.aleandro.financialtest.unittests.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.aleandro.financial.UserDebt.DTO.DebtDto;
import com.aleandro.financial.UserDebt.model.Debt;
import com.aleandro.financial.UserDebt.repository.DebtRepository;
import com.aleandro.financial.UserDebt.service.DebtService;
import com.aleandro.financialtest.unittests.fixtures.DebtMocks;

@ExtendWith(MockitoExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
public class DebtServiceTest {
	
	@InjectMocks
	private DebtService service;
	
	@Mock
	private DebtRepository repository;
	
	static Debt debt;

	@BeforeEach
	void setUp() throws Exception {
		debt = DebtMocks.mock_one_debt();
	}

	@Test
	void testGet_debt_by_id() {
		when(repository.CustomfindByUser_idAndDebt_id(debt.getUser_id().getId(),debt.getId())).thenReturn(Optional.of(debt));
		DebtDto found_debt = service.get_debt_by_id(debt.getId(), debt.getUser_id().getId());
		assertNotNull(found_debt);
		assertNotNull(found_debt.getId());
		assertEquals(found_debt.getId(), debt.getId());
		assertEquals(found_debt.getData_pagamento(), debt.getData_pagamento());
		assertEquals(found_debt.getDestino(), debt.getDestino());
		assertEquals(found_debt.getPaga(), debt.getPaga());
		assertEquals(found_debt.getUser_id(), debt.getUser_id().getId());
		assertEquals(found_debt.getValor(), debt.getValor());
		assertEquals(found_debt.getCreated_at(), debt.getCreated_at());
		assertEquals(found_debt.getUpdated_at(), debt.getUpdated_at());
	}

	@Test
	void testGet_user_debts() {
		when(repository.CustomfindByUser_id(debt.getUser_id().getId())).thenReturn(DebtMocks.mock_many_debts(5));
		List<DebtDto> lista_dividas =  service.get_user_debts(debt.getUser_id().getId());
		for(DebtDto found_debt : lista_dividas) {	
			assertNotNull(found_debt);
			assertNotNull(found_debt.getId());
			assertEquals(found_debt.getId(), debt.getId());
			assertEquals(found_debt.getData_pagamento(), debt.getData_pagamento());
			assertEquals(found_debt.getDestino(), debt.getDestino());
			assertEquals(found_debt.getPaga(), debt.getPaga());
			assertEquals(found_debt.getUser_id(), debt.getUser_id().getId());
			assertEquals(found_debt.getValor(), debt.getValor());
	}



}
}