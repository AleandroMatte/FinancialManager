package com.aleandro.financial.DTO;

import java.util.Date;

import com.aleandro.financial.models.TipoDividas;
import com.aleandro.financial.models.User;
import com.github.dozermapper.core.Mapping;

public class DebtDto  extends BaseDto{
	
	private Long user_id;
	private Double valor;
	private String destino;
	private Boolean paga;
	private TipoDividas recorrencia;
	private Date data_pagamento;

	public DebtDto() {
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

	public Boolean getPaga() {
		return paga;
	}

	public void setPaga(Boolean paga) {
		this.paga = paga;
	}

	@Mapping(value = "tipo_dividas_id")
	public TipoDividas getRecorrencia() {
		return recorrencia;
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

	public void setRecorrencia(TipoDividas recorrencia) {
		this.recorrencia = recorrencia;
		
	}


		// TODO Auto-generated method stub
		
	}
	
	
