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

import com.aleandro.financial.User.Repository.UserRepository;
import com.aleandro.financial.User.infra.UserDto;
import com.aleandro.financial.User.models.User;
import com.aleandro.financial.User.services.UserServices;
import com.aleandro.financialtest.unittests.fixtures.UserMocks;


@ExtendWith(MockitoExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
class UserServicesTest {
	
	@InjectMocks
	private UserServices service;
	@Mock
	private UserRepository repository;
	
	@Mock
	static User user;
	

	
	@BeforeEach()
	void setUp(){
		user = UserMocks.mock_one_user();
		
	}


	@Test
	void testFindById() {
		when(repository.findById(user.getId())).thenReturn(Optional.of(user));
		UserDto result = service.findById(user.getId());
		assertNotNull(result);
		assertNotNull(result.getId());
		assertEquals(user.getId(), result.getId());
		assertEquals(user.getEmail(), result.getEmail());
		assertEquals(user.getName(), result.getName());
		assertEquals(user.getUpdated_at(),result.getUpdated_at());
		assertEquals(user.getCreated_at(), result.getCreated_at());
	}

	@Test
	void testGet_all_users() {
		when(repository.findAll()).thenReturn(UserMocks.mock_multiple_users(4));
		List<UserDto> users = service.get_all_users();
		assertNotNull(users);
		for (UserDto userDto : users) {
			assertNotNull(userDto);
			assertEquals(user.getId(), userDto.getId());
			assertEquals(user.getEmail(), userDto.getEmail());
			assertEquals(user.getName(), userDto.getName());
			assertEquals(user.getUpdated_at(),userDto.getUpdated_at());
			assertEquals(user.getCreated_at(), userDto.getCreated_at());
			
		}
	}




}
