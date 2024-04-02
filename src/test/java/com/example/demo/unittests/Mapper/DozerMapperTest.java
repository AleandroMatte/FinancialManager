package com.example.demo.unittests.Mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.aleandro.financial.DTO.DozerMapper;
import com.aleandro.financial.DTO.UserDto;
import com.aleandro.financial.models.User;




@RunWith(SpringJUnit4ClassRunner.class)
class DozerMapperTest {
	
	@Mock private User user_mock;
	@Mock private UserDto user_dto_mock;
	private static final Logger logger = Logger.getLogger(DozerMapperTest.class.getName());
	
	
	@Test
	@BeforeEach
	void contextLoads() {
		this.user_mock  = mock(User.class);
		this.user_dto_mock  = mock(UserDto.class);
	}
	
	
	@Test
	void UserToVoTest() {
		CustomLogging.log_info(logger,"\n\t starting " + TestingHelpers.get_method_to_run()+ "\n\t");
		UserDto output_object = DozerMapper.parseObject(user_mock ,UserDto.class);
		try {
			assertEquals(user_mock.getEmail(),output_object.getEmail());
			assertEquals(user_mock.getName(),output_object.getName());
			assertEquals(user_mock.getCreated_at(),output_object.getCreated_at());
			assertEquals(user_mock.getId(),output_object.getId());
			assertEquals(user_mock.getUpdated_at(),output_object.getUpdated_at());
		
		} catch (Throwable e) {
			CustomLogging.log_failure(logger,"\n\t Test for " + TestingHelpers.get_method_to_run()+" Failed \n\t");
			throw e;
	  }
		CustomLogging.log_success(logger,"\n\t Test for " + TestingHelpers.get_method_to_run()+" Succeded \n\t");
	}
	
	@Test
	void ListUserToVoTest() {
		CustomLogging.log_info(logger,"\n\t starting " + TestingHelpers.get_method_to_run()+ "\n\t");
		List<User> list_user_entities = new ArrayList<>();
		for(int i=0;i<5;i++) {
			User user_entity_mocks = mock(User.class);
			list_user_entities.add(user_entity_mocks);
		}
		List<UserDto> list_vo_objects = DozerMapper.parseListObjects(list_user_entities,UserDto.class);
		// i wish it wasn't like this but java doesn't have the enumerate function ):   
		try {
			for(int index = 0; index<list_vo_objects.size();index++) {
				assertEquals(list_vo_objects.get(index).getEmail(),list_user_entities.get(index).getEmail());
				assertEquals(list_vo_objects.get(index).getName(),list_user_entities.get(index).getName());
				assertEquals(list_vo_objects.get(index).getCreated_at(),list_user_entities.get(index).getCreated_at());
				assertEquals(list_vo_objects.get(index).getId(),list_user_entities.get(index).getId());
				assertEquals(list_vo_objects.get(index).getUpdated_at(),list_user_entities.get(index).getUpdated_at());
			}
		} catch (Throwable e) {
			CustomLogging.log_failure(logger,"\n\t Test for " + TestingHelpers.get_method_to_run()+" Failed \n\t");
			throw e;
		}
		CustomLogging.log_success(logger,"\n\t Test for " + TestingHelpers.get_method_to_run()+" Succeded \n\t");
	}
	
	
	@Test
	void VoToUserEntityTest() {
		CustomLogging.log_info(logger,"\n\t starting " + TestingHelpers.get_method_to_run()+ "\n\t");
		User output_object = DozerMapper.parseObject(user_dto_mock ,User.class);
		try {
			assertEquals(user_mock.getEmail(),output_object.getEmail());
			assertEquals(user_mock.getName(),output_object.getName());
			assertEquals(user_mock.getCreated_at(),output_object.getCreated_at());
			assertEquals(user_mock.getId(),output_object.getId());
			assertEquals(user_mock.getUpdated_at(),output_object.getUpdated_at());
			CustomLogging.log_success(logger,"\n\t Test for " + TestingHelpers.get_method_to_run()+" Succeded \n\t");
			
		} catch (Throwable e) {
			CustomLogging.log_failure(logger,"\n\t Test for " + TestingHelpers.get_method_to_run()+" Failed \n\t");
			throw e;
		}
		
	}
	
	@Test
	void ListVoToEntityListTest() {
		CustomLogging.log_info(logger,"\n\t starting " + TestingHelpers.get_method_to_run()+ "\n\t");
		List<UserDto> list_vo_objects = new ArrayList<>();
		for(int i=0;i<5;i++) {
			UserDto user_entity_mocks = mock(UserDto.class);
			list_vo_objects.add(user_entity_mocks);
		}
		List<User> list_user_entities = DozerMapper.parseListObjects(list_vo_objects ,User.class);
		try {
			for(int index = 0; index<list_user_entities.size();index++) {
				assertEquals(list_user_entities.get(index).getEmail(),list_vo_objects.get(index).getEmail());
				assertEquals(list_user_entities.get(index).getName(),list_vo_objects.get(index).getName());
				assertEquals(list_user_entities.get(index).getCreated_at(),list_vo_objects.get(index).getCreated_at());
				assertEquals(list_user_entities.get(index).getId(),list_vo_objects.get(index).getId());
				assertEquals(list_user_entities.get(index).getUpdated_at(),list_vo_objects.get(index).getUpdated_at());
			}
		} catch (Throwable e) {
			CustomLogging.log_failure(logger,"\n\t Test for " + TestingHelpers.get_method_to_run()+" Failed \n\t");
			throw e;
		}
		CustomLogging.log_success(logger,"\n\t Test for " + TestingHelpers.get_method_to_run()+" Succeded \n\t");
	}

}

