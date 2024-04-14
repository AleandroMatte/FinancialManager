package com.example.demo.unittests.linkGeneration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.Collections;
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


@ExtendWith(MockitoExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
class UserServicesTest {
	
	@InjectMocks
	private UserServices service;
	@Mock
	private UserRepository repository;
	
	static User user;
	

	
	@BeforeEach
	void setUp(){
		user = new User();
		user.setId(1L);
		user.setEmail("aleandro_test@test.com");
		user.setName("Test_user");
		user.setCreated_at(Timestamp.valueOf("2024-03-26 16:52:50.338000"));
		user.setUpdated_at(Timestamp.valueOf("2024-03-26 16:52:50.338000"));
	}


	@Test
	void testFindById() {
		when(repository.findById(user.getId())).thenReturn(Optional.of(user));
		UserDto result = service.findById(1L);
		assertNotNull(result);
		assertNotNull(result.getId());
		assertTrue(result.toString().contains("links: [</user/1>;rel=\"self\";name=\"Actions\"]"));
		assertEquals(user.getName(), result.getName());
		assertEquals(user.getEmail(), result.getEmail());
		assertEquals(user.getId(), result.getId());
		assertEquals(user.getCreated_at(), result.getCreated_at());
		assertEquals(user.getUpdated_at(), result.getUpdated_at());
	}

	@Test
	void testGet_all_users() {
		when(repository.findAll()).thenReturn(Collections.singletonList(user));
		List<UserDto> users = service.get_all_users();
		assertNotNull(users);
		for (UserDto userDto : users) {
			assertTrue(userDto.toString().contains("links: [</user/1>;rel=\"self\";name=\"Actions\"]"));
			
		}
	}




}
