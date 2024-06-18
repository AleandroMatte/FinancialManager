package com.aleandro.financial.UserWin.infra;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class WinAnalyticsData {
	private ArrayList<WinAnalyticsNode> win_data;
	private String nome;
	private Double total_data_amount = 0d;
	
	
	
	
	public WinAnalyticsData(WinningsDto win_data, String nome) {
		this.nome = nome;
		this.win_data = new ArrayList<>();
		this.win_data.add(new WinAnalyticsNode(win_data));
		this.total_data_amount += win_data.getValor();
	}
	
	public void addAnaliticNode(WinningsDto win_dto) {
		this.win_data.add(new WinAnalyticsNode(win_dto));
		this.total_data_amount += win_dto.getValor();
	}
	
	public HashMap<String, Object> serialize(){
		HashMap<String, Object>serialized =  new HashMap<>();
		serialized.put("label", this.nome);
		serialized.put("total_amout_of_data", this.total_data_amount);
		return serialized;
		
	}




	public class WinAnalyticsNode{
		private Double valor;
		private String origem;
		private Date data_recebimento;
		
		public WinAnalyticsNode(WinningsDto win_Dto) {
			this.valor = win_Dto.getValor();
			this.origem = win_Dto.getOrigem();
			this.data_recebimento = win_Dto.getData_recebimento();
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
		
		
		@Override
		public String toString() {
			return "WinAnalyticsNode [valor=" + valor + ", origem=" + origem + ", data_recebimento=" + data_recebimento
					+ "]";
		}
		
		
		
		
		
		
	}
	
	
	
	
	
	
	

}
