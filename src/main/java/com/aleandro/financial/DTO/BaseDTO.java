package com.aleandro.financial.DTO;

import java.sql.Timestamp;


public abstract class BaseDTO {
	
	protected long id;
	protected Timestamp created_at =  new Timestamp(System.currentTimeMillis());
	protected Timestamp updated_at = new Timestamp(System.currentTimeMillis());

	public BaseDTO() {
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Timestamp getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}

	public Timestamp getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Timestamp updated_at) {
		this.updated_at = updated_at;
	}
	
	

}