package com.example.demo.unittests.linkGeneration;

import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.aleandro.financial.UserDebt.repository.DebtRepository;
import com.aleandro.financial.UserDebt.service.DebtService;

@ExtendWith(MockitoExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
public class DebtServicesTest {
	
	@InjectMocks
	private DebtService service;
	
	@Mock
	private DebtRepository repository;

	public DebtServicesTest() {
		// TODO Auto-generated constructor stub
	}

}
