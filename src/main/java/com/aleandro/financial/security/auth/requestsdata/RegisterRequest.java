package com.aleandro.financial.security.auth.requestsdata;

public class RegisterRequest {
	private String first_name;
	private String last_name;
	private String user_name;
	private String password;
	private String role;
	
	public RegisterRequest() {
	}
	public RegisterRequest(String first_name, String last_name, String user_name, String password,
			String role) {
		this.first_name = first_name;
		this.last_name = last_name;
		this.user_name = user_name;
		this.password = password;
		this.role = role;
	}
	
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "RegisterRequest [first_name=" + first_name + ", last_name=" + last_name + ", user_name=" + user_name
				+ ", password=" + password + ", role=" + role + "]";
	}

	
	
}
