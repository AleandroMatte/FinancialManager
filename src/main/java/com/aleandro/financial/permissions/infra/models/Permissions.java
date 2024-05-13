package com.aleandro.financial.permissions.infra.models;

import java.util.List;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;

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
@Table(name="permission")
public class Permissions implements GrantedAuthority {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private String description;
	
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_permission",
	joinColumns = {@JoinColumn (name="id_permission")},
	inverseJoinColumns = {@JoinColumn (name = "id_user")})
	private List<Permissions> permissions;

	public Permissions() {
		// TODO Auto-generated constructor stub
	}
	

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return this.description;
	}


	@Override
	public int hashCode() {
		return Objects.hash(description, id);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Permissions other = (Permissions) obj;
		return Objects.equals(description, other.description) && Objects.equals(id, other.id);
	}

	
}
