package com.aleandro.financial.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity(name = "recebimentos")
@Table(name = "recebimentos")
public class Recebimentos extends BaseModel {
	
	@Column(name = "valor", nullable = false)
	private Double valor;
	
	@Column(name = "origem")
	private String origem;
	
	@Column(name = "recebida", nullable = false)
	private Boolean recebida;
	
	@ManyToOne
	@JoinColumn(name = "tipo_recebimento_id", referencedColumnName = "id")
	private TipoRecebimento recorrencia;

	public Boolean getRecebida() {
		return recebida;
	}

	public void setRecebida(Boolean recebida) {
		this.recebida = recebida;
	}

	public TipoRecebimento getRecorrencia() {
		return recorrencia;
	}

	public void setRecorrencia(TipoRecebimento recorrencia) {
		this.recorrencia = recorrencia;
	}

	public Recebimentos() {
		// TODO Auto-generated constructor stub
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getOrigem() {
		return origem;
	}

	public void setOrigem(String origem) {
		this.origem = origem;
	}
	
	

}
