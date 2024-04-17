package com.aleandro.financialtest.unittests.fixtures;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.aleandro.financial.User.models.User;

public  class UserMocks {
	
	public static User mock_one_user() {
		User mocked = new User();
		mocked.setId(1L);
		mocked.setCreated_at(Timestamp.from(Instant.EPOCH));
		mocked.setUpdated_at(Timestamp.from(Instant.EPOCH));
		mocked.setEmail("aleandro_test@test.com");
		mocked.setName("Test_user");
		return mocked ;
		
	}
	
	public static List<User> mock_multiple_users(int num_users){
		List<User> mocked_list = new ArrayList<>();
		int rep_counter=0;
		while(rep_counter<num_users) {
			mocked_list.add(mock_one_user());
			rep_counter++;
		}
		return mocked_list;
	}
	
	
	

}
