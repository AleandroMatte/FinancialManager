package com.aleandro.financial.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity(name = "tipo_recebimento")
@Table(name = "tipo_recebimento")
public class TypeWinning extends BaseModel {
	
	@Column(name = "recorrência", nullable = false)
	private String recorrencia;
	
	

	public TypeWinning() {
		// TODO Auto-generated constructor stub
	}


	public String getRecorrencia() {
		return recorrencia;
	}


	public void setRecorrencia(String recorrencia) {
		this.recorrencia = recorrencia;
	}

}
