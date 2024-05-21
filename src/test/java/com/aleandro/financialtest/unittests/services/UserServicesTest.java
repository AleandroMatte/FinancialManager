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


import com.aleandro.financial.UserSec.infra.dto.UserSecDto;
import com.aleandro.financial.UserSec.infra.models.UserSecModel;
import com.aleandro.financial.UserSec.repositories.UserSecRepository;
import com.aleandro.financial.UserSec.services.UserSecServices;
import com.aleandro.financialtest.unittests.fixtures.UserMocks;


@ExtendWith(MockitoExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
class UserServicesTest {
	
	@InjectMocks
	private UserSecServices service;
	@Mock
	private UserSecRepository repository;
	
	@Mock
	static UserSecModel user;
	

	
	@BeforeEach()
	void setUp(){
		user = UserMocks.mock_one_user();
		
	}


	@Test
	void testFindById() {
		when(repository.findById(user.getId())).thenReturn(Optional.of(user));
		UserSecDto result = service.findById(user.getId());
		assertNotNull(result);
		assertNotNull(result.getId());
		assertEquals(user.getId(), result.getId());

	}

	@Test
	void testGet_all_users() {
		when(repository.findAll()).thenReturn(UserMocks.mock_multiple_users(4));
		List<UserSecDto> users = service.get_all_users();
		assertNotNull(users);
		for (UserSecDto userDto : users) {
			assertNotNull(userDto);
			assertEquals(user.getId(), userDto.getId());

			
		}
	}




}
