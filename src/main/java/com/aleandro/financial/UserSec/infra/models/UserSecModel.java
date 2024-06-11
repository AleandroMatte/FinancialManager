package com.aleandro.financial.UserSec.infra.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.aleandro.financial.permissions.infra.models.Permissions;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="users")
public class UserSecModel  implements UserDetails{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE)
	private Long id;
	@Column(nullable = false, unique = true)
	private String user_name;
	@Column(nullable = false)
	private String full_name;
	@Column(nullable = false, unique = true)
	private String password;
	@Column(nullable = false)
	private boolean account_non_expired;
	@Column(nullable = false)
	private boolean account_non_locked;
	@Column(nullable = false)
	private boolean credentials_non_expired;
	@Column(nullable = false)
	private boolean enabled;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_permission",
	joinColumns = {@JoinColumn (name="id_user")},
	inverseJoinColumns = {@JoinColumn (name = "id_permission")})
	private List<Permissions> permissions;
	
	
	public UserSecModel() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public UserSecModel(String user_name, String full_name, String password, List<Permissions> permissions) {
		super();
		this.user_name = user_name;
		this.full_name = full_name;
		this.password = password;
		this.permissions = permissions;
	}



	public List<String> getPermissions(){
		List<String> roles = new ArrayList<>();
		for (Permissions permission : this.permissions) {
			roles.add(permission.getDescription());
		}
		return roles;
	}
	public void setPermissions(List<Permissions> permissions){
		this.permissions = permissions;
	}
	

	

	public Long getId() {
		return id;
	}




	public void setId(Long id) {
		this.id = id;
	}



	public String getUser_name() {
		return user_name;
	}



	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}



	public String getFull_name() {
		return full_name;
	}



	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}



	public boolean isAccount_non_expired() {
		return account_non_expired;
	}



	public void setAccount_non_expired(boolean account_non_expired) {
		this.account_non_expired = account_non_expired;
	}



	public boolean isAccount_non_locked() {
		return account_non_locked;
	}



	public void setAccount_non_locked(boolean account_non_locked) {
		this.account_non_locked = account_non_locked;
	}



	public boolean isCredentials_non_expired() {
		return credentials_non_expired;
	}



	public void setCredentials_non_expired(boolean credentials_non_expired) {
		this.credentials_non_expired = credentials_non_expired;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}



	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return this.permissions;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.user_name;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return this.account_non_expired;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return this.account_non_locked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return this.credentials_non_expired;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return this.enabled;
	}


	}
	
	
