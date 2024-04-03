package com.aleandro.financial.DTO;

import java.util.Date;

import com.github.dozermapper.core.Mapping;

public class WinningsDto extends BaseDto {

	private Double valor;
	private String origem;
	private Boolean recebida;
	private Long recorrencia_id;
	private Date data_recebimento;
	private Long user_id;
	
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
	public Long getRecorrencia() {
		return recorrencia_id;
	}
	public void setRecorrencia(Long recorrencia) {
		this.recorrencia_id = recorrencia;
	}
	public Date getData_recebimento() {
		return data_recebimento;
	}
	public void setData_recebimento(Date data_recebimento) {
		this.data_recebimento = data_recebimento;
	}
	@Mapping(value = "user_id")
	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
}
