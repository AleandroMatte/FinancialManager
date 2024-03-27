package com.aleandro.financial.models;

import java.util.Date;

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
	
	@Column(name="data_recebimento")
	private Date data_recebimento;
	
	@ManyToOne
	@JoinColumn(name = "fk_user_id", referencedColumnName = "id")
	private User user_id;

	public User getUser_id() {
		return user_id;
	}

	public void setUser_id(User user_id) {
		this.user_id = user_id;
	}

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

	public Date getData_recebimento() {
		return data_recebimento;
	}

	public void setData_recebimento(Date data_recebimento) {
		this.data_recebimento = data_recebimento;
	}
	
	
	

}
