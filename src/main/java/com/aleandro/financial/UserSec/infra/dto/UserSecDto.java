package com.aleandro.financial.UserSec.infra.dto;

import java.util.List;

import com.aleandro.financial.permissions.infra.models.Permissions;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class UserSecDto {
	@JsonIgnoreProperties(value = {"id", "password", "permissions"})
	private Long id;
	private String user_name;
	private String full_name;
	private String password;
	private boolean account_non_expired;
	private boolean account_non_locked;
	private boolean credentials_non_expired;
	private boolean enabled;
	private List<Permissions> permissions;
	
	

	
	
	public Long getId() {
		return id;
	}

	public String getUser_name() {
		return user_name;
	}

	public String getFull_name() {
		return full_name;
	}

	public String getPassword() {
		return password;
	}

	public boolean isAccount_non_expired() {
		return account_non_expired;
	}

	public boolean isAccount_non_locked() {
		return account_non_locked;
	}

	public boolean isCredentials_non_expired() {
		return credentials_non_expired;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public List<Permissions> getPermissions() {
		return permissions;
	}

	public UserSecDto() {
		
	}
	
	public UserSecDto(Builder builder) {
		this.id = builder.id;
		this.user_name = builder.username;
		this.password = builder.password;
		this.credentials_non_expired = builder.credentials_non_expired;
		this.enabled = builder.enabled;
		this.permissions = builder.permissions;
		this.full_name = builder.full_name;
		this.account_non_expired = builder.account_non_expired;
		this.account_non_locked = builder.account_non_locked;
	}

	public static class Builder{
		private final String username;
		private final String full_name;
		private final String password;
		private Long id;
		private boolean account_non_expired;
		private boolean account_non_locked;
		private boolean credentials_non_expired;
		private boolean enabled;
		private List<Permissions> permissions;
		
		public Builder(String username,String password, String full_name) {
			this.username=username;
			this.password=password;
			this.full_name=full_name;
			
		}
		
		public Builder id(Long id) {
			this.id(id);
			return this;
		}
		public Builder account_non_expired(boolean account_non_expired) {
			this.account_non_expired(account_non_expired);
			return this;
		}
		public Builder account_non_locked(boolean account_non_locked) {
			this.account_non_locked(account_non_locked);
			return this;
		}
		public Builder credentials_non_expired(boolean credentials_non_expired) {
			this.credentials_non_expired(credentials_non_expired);
			return this;
		}
		public Builder enabled(boolean enabled) {
			this.enabled(enabled);
			return this;
		}
		
		public UserSecDto build() {
			return new UserSecDto(this);
		}
	}
}
