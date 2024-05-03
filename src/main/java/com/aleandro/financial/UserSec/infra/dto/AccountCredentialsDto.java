package com.aleandro.financial.UserSec.infra.dto;

import java.io.Serializable;
import java.util.List;

public class AccountCredentialsDto implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String user_name;
	
    private List<String> roles;

	public AccountCredentialsDto() {
		// TODO Auto-generated constructor stub
	}
	
	
	

	public AccountCredentialsDto(String user_name, List<String> roles) {
		super();
		this.user_name = user_name;
		this.roles = roles;
	}




	public String getName() {
		return user_name;
	}

	public void setName(String name) {
		this.user_name = name;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	
	

}
