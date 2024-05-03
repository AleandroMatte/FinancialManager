package com.aleandro.financial.UserSec.infra.dto;

import java.util.Date;
import java.util.Objects;

public class CredentialResponseVo {
	
	private String user_name;
	private Boolean authenticated;
	private Date created;
	private Date expiration;
	private String accessToken;
	private String refreshToken;
	
	

	public CredentialResponseVo(String user_name, Boolean authenticated, Date created, Date expiration,
			String accessToken, String refreshToken) {
		super();
		this.user_name = user_name;
		this.authenticated = authenticated;
		this.created = created;
		this.expiration = expiration;
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
	}



	public CredentialResponseVo() {
		// TODO Auto-generated constructor stub
	}



	public String getUser_name() {
		return user_name;
	}



	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}



	public Boolean getAuthenticated() {
		return authenticated;
	}



	public void setAuthenticated(Boolean authenticated) {
		this.authenticated = authenticated;
	}



	public Date getCreated() {
		return created;
	}



	public void setCreated(Date created) {
		this.created = created;
	}



	public Date getExpiration() {
		return expiration;
	}



	public void setExpiration(Date expiration) {
		this.expiration = expiration;
	}



	public String getAccessToken() {
		return accessToken;
	}



	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}



	public String getRefreshToken() {
		return refreshToken;
	}



	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}



	@Override
	public int hashCode() {
		return Objects.hash(accessToken, authenticated, created, expiration, refreshToken, user_name);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CredentialResponseVo other = (CredentialResponseVo) obj;
		return Objects.equals(accessToken, other.accessToken) && Objects.equals(authenticated, other.authenticated)
				&& Objects.equals(created, other.created) && Objects.equals(expiration, other.expiration)
				&& Objects.equals(refreshToken, other.refreshToken) && Objects.equals(user_name, other.user_name);
	}
	
	
	

}
