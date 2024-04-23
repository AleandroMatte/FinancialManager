package com.aleandro.financial.UserWin.infra;

import java.util.Date;
import java.util.UUID;

import com.aleandro.financial.shared.infra.BaseDto;
import com.fasterxml.jackson.annotation.JsonAlias;

public class WinningsDto extends BaseDto {

	private Double valor;
	private String origem;
	private Boolean recebida;
	@JsonAlias({"recorrencia"})
	private UUID recorrencia_id;
	private Date data_recebimento;
	private UUID user_id;
	
	public WinningsDto() {
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
	
	
	public UUID getRecorrencia() {
		return recorrencia_id;
	}
	public void setRecorrencia(UUID recorrencia) {
		this.recorrencia_id = recorrencia;
	}
	public Date getData_recebimento() {
		return data_recebimento;
	}
	public void setData_recebimento(Date data_recebimento) {
		this.data_recebimento = data_recebimento;
	}
	public UUID getUser_id() {
		return user_id;
	}

	
	public void setUser_id(UUID user_id) {
		this.user_id = user_id;
	}
	@Override
	public String toString() {
		return "WinningsDto [valor=" + valor + ", origem=" + origem + ", recebida=" + recebida + ", recorrencia_id="
				+ recorrencia_id + ", data_recebimento=" + data_recebimento + ", user_id=" + user_id + "]";
	}
	
	
}
