package com.aleandro.financial.DTO;

import java.util.Date;

import com.aleandro.financial.models.TipoRecebimento;
import com.aleandro.financial.models.User;
import com.github.dozermapper.core.Mapping;

public class RecebimentosDto {

	private Double valor;
	private String origem;
	private Boolean recebida;
	private TipoRecebimento recorrencia;
	private Date data_recebimento;
	private User user_id;
	
	public RecebimentosDto() {
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
	public Date getData_recebimento() {
		return data_recebimento;
	}
	public void setData_recebimento(Date data_recebimento) {
		this.data_recebimento = data_recebimento;
	}
	@Mapping(value = "user_id")
	public User getUser_id() {
		return user_id;
	}

	public void setUser_id(User user_id) {
		this.user_id = user_id;
	}
}
