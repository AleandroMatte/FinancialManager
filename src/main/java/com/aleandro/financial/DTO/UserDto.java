package com.aleandro.financial.DTO;

public class UserDto extends BaseDTO{

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
