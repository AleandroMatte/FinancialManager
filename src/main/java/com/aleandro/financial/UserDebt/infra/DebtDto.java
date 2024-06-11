package com.aleandro.financial.UserDebt.infra;

import java.util.Date;
import com.aleandro.financial.shared.infra.BaseDto;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;

@JsonPropertyOrder({"valor", "destino", "paga","user_id","data_pagamento"})
public class DebtDto  extends BaseDto{
	
	private Long user_id;
	private Double valor;
	private String destino;
	private Boolean paga;
	private Long recorrencia_id;
	private Date data_pagamento;

	public DebtDto() {
		// TODO Auto-generated constructor stub
	}
	
	
	





	public Long getRecorrencia_id() {
		return recorrencia_id;
	}



	public void setRecorrencia_id(Long recorrencia_id) {
		this.recorrencia_id = recorrencia_id;
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

	public Boolean getPaga() {
		return paga;
	}

	public void setPaga(Boolean paga) {
		this.paga = paga;
	}


	public Date getData_pagamento() {
		return data_pagamento;
	}

	public void setData_pagamento(Date data_pagamento) {
		this.data_pagamento = data_pagamento;
	}

	@Mapping(value = "user_id")
	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	


		// TODO Auto-generated method stub
		
	}
	
	