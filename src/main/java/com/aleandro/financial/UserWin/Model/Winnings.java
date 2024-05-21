package com.aleandro.financial.UserWin.Model;

import java.util.Date;

import com.aleandro.financial.UserSec.infra.models.UserSecModel;
import com.aleandro.financial.shared.model.BaseModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity(name = "recebimentos")
@Table(name = "recebimentos")
public class Winnings extends BaseModel {
	
	@Column(name = "valor", nullable = false)
	private Double valor;
	
	@Column(name = "origem")
	private String origem;
	
	@Column(name = "recebida", nullable = false)
	private Boolean recebida;
	
	@ManyToOne
	@JoinColumn(name = "tipo_recebimento_id", referencedColumnName = "id")
	private TypeWinning recorrencia;
	
	@Column(name="data_recebimento")
	private Date data_recebimento;
	
	@ManyToOne
	@JoinColumn(name = "fk_user_id", referencedColumnName = "id")
	private UserSecModel user;

	public UserSecModel getUser() {
		return user;
	}

	public void setUser(UserSecModel user_id) {
		this.user = user_id;
	}

	public Boolean getRecebida() {
		return recebida;
	}

	public void setRecebida(Boolean recebida) {
		this.recebida = recebida;
	}

	public TypeWinning getRecorrencia() {
		return recorrencia;
	}

	public void setRecorrencia(TypeWinning recorrencia) {
		this.recorrencia = recorrencia;
	}

	public Winnings() {
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
