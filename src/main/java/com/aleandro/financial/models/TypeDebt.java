package com.aleandro.financial.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity(name = "tipo_dividas")
@Table(name = "tipo_dividas")
public class TypeDebt extends BaseModel {
	
	@Column(name = "tipo_divida", nullable = false)
	private String tipoDivida;

	public TypeDebt() {
		// TODO Auto-generated constructor stub
	}

}
