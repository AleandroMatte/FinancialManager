package com.aleandro.financial.UserWin.infra;

import java.util.Date;
import com.aleandro.financial.shared.infra.BaseDto;
import com.fasterxml.jackson.annotation.JsonAlias;

public class WinningsDto extends BaseDto {

	private Double valor;
	private String origem;
	private Boolean recebida;
	@JsonAlias({"recorrencia"})
	private Long recorrencia_id;
	private Date data_recebimento;
	private Long user_id;
	
	public WinningsDto() {
		// TODO Auto-generated constructor stub
	}
	
	
	public WinningsDto(WinningsDto other_dto) {
		super();
		this.valor = other_dto.getValor();
		this.origem = other_dto.getOrigem();
		this.recebida = other_dto.getRecebida();
		this.recorrencia_id = other_dto.getRecorrencia();
		this.data_recebimento = other_dto.getData_recebimento();
		this.user_id = other_dto.getUser_id();
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
	public Long getUser_id() {
		return user_id;
	}

	
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	@Override
	public String toString() {
		return "WinningsDto [valor=" + valor + ", origem=" + origem + ", recebida=" + recebida + ", recorrencia_id="
				+ recorrencia_id + ", data_recebimento=" + data_recebimento + ", user_id=" + user_id + "]";
	}
	
	
}
