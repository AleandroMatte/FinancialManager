package com.aleandro.financial.models;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity(name = "user")
@Table(name = "user")
public class User extends BaseModel {
	
	@Column(name = "username", nullable = false)
	private String name;
	@Column(name = "email", nullable = false)
	private String email;
	

	public User() {
		
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


	@Override
	public int hashCode() {
		return Objects.hash(email, name);
	}
	


	@Override
	public String toString() {
		return "User [\nid=" + id + "\nname=" + name + ", \nemail=" + email + "]";
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(email, other.email) && Objects.equals(name, other.name);
	}
	
	
	

}
