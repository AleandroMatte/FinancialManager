package com.aleandro.financial.models;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Table(name = "dividas")
@Entity(name = "dividas")
public class Dividas extends BaseModel {
	
	@Column(name = "valor", nullable = false)
	private Double valor;
	
	@Column(name = "destino")
	private String destino;
	
	@Column(name = "paga", nullable = false)
	private Boolean paga;
	
	@ManyToOne
	@JoinColumn(name = "tipo_dividas_id", referencedColumnName = "id")
	private TipoDividas recorrencia;
	
	@Column(name="data_pagamento")
	private Date data_pagamento;

	@ManyToOne
	@JoinColumn(name = "fk_user_id", referencedColumnName = "id", nullable = false)
	private User user_id;
	
	
	
	

	@Override
	public String toString() {
		return "Dividas [valor=" + valor + ", destino=" + destino + ", paga=" + paga + ", recorrencia=" + recorrencia
				+ ", data_pagamento=" + data_pagamento + ", user_id=" + user_id + "]";
	}


	public User getUser_id() {
		return user_id;
	}


	public void setUser_id(User user_id) {
		this.user_id = user_id;
	}


	public Boolean getPaga() {
		return paga;
	}


	public void setPaga(Boolean paga) {
		this.paga = paga;
	}


	public TipoDividas getRecorrencia() {
		return recorrencia;
	}


	public void setRecorrencia(TipoDividas recorrencia) {
		this.recorrencia = recorrencia;
	}


	public Dividas() {
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
	
	
	

}
