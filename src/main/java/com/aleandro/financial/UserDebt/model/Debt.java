package com.aleandro.financial.UserDebt.model;

import java.util.Date;

import com.aleandro.financial.UserSec.infra.models.UserSecModel;
import com.aleandro.financial.shared.model.BaseModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Table(name = "dividas")
@Entity(name = "dividas")
public class Debt extends BaseModel {
	
	@Column(name = "valor", nullable = false)
	private Double valor;
	
	@Column(name = "destino")
	private String destino;
	
	@Column(name = "paga", nullable = false)
	private Boolean paga;
	
	@ManyToOne
	@JoinColumn(name = "tipo_dividas_id", referencedColumnName = "id")
	private TypeDebt recorrencia;
	
	@Column(name="data_pagamento")
	private Date data_pagamento;

	@ManyToOne
	@JoinColumn(name = "fk_user_id", referencedColumnName = "id", nullable = false)
	private UserSecModel user;
	
	
	
	


	public UserSecModel getUser_id() {
		return user;
	}
	



	public void setUser_id(UserSecModel user) {
		this.user = user;
	}


	public Boolean getPaga() {
		return paga;
	}


	public void setPaga(Boolean paga) {
		this.paga = paga;
	}


	public TypeDebt getRecorrencia() {
		return recorrencia;
	}


	public void setRecorrencia(TypeDebt recorrencia) {
		this.recorrencia = recorrencia;
	}


	public Debt() {
		// TODO Auto-generated constructor stub
	}


	public Double getValor() {
		return valor;
	}


	public void setValor(Double valor) {
		this.valor = valor;
	}


	public String getDestino() {
		return destino;
	}


	public void setDestino(String destino) {
		this.destino = destino;
	}


	public Date getData_pagamento() {
		return data_pagamento;
	}


	public void setData_pagamento(Date data_pagamento) {
		this.data_pagamento = data_pagamento;
	}
	
	
	
	
	
	@Override
	public String toString() {
		return "Dividas [valor=" + valor + ", destino=" + destino + ", paga=" + paga + ", recorrencia=" + recorrencia
				+ ", data_pagamento=" + data_pagamento + ", user_id=" + user.getId() + "]";
	}
	

}
