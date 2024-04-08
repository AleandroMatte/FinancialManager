package com.aleandro.financial.User.infra;

import com.aleandro.financial.shared.infra.BaseDto;

public class UserDto extends BaseDto{

		private String name;
		private String email;
		

		public UserDto() {
			
		}


		public String getName() {
			return name;
		}


		public void setName(String name) {
			this.name = name;
		}


		public String getEmail() {
			return email;
		}


		public void setEmail(String email) {
			this.email = email;
		}


	}
