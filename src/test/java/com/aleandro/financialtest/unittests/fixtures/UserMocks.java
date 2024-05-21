package com.aleandro.financialtest.unittests.fixtures;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import com.aleandro.financial.UserSec.infra.models.UserSecModel;

public  class UserMocks {
	
	public static UserSecModel mock_one_user() {
		UserSecModel mocked = new UserSecModel();
		mocked.setId(1L);
		return mocked ;
		
	}
	
	public static List<UserSecModel> mock_multiple_users(int num_users){
		List<UserSecModel> mocked_list = new ArrayList<>();
		int rep_counter=0;
		while(rep_counter<num_users) {
			mocked_list.add(mock_one_user());
			rep_counter++;
		}
		return mocked_list;
	}
	
	
	

}
