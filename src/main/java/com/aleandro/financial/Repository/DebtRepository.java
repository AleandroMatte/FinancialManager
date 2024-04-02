package com.aleandro.financial.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.aleandro.financial.models.Dividas;

public interface DebtRepository  extends BaseInterface<Dividas>{
	
	@Query(value = "select * from dividas where dividas.fk_user_id = ?1", nativeQuery = true)
	List<Dividas> CustomfindByUser_id(Long user_id);
	
	@Query(value = "select * from dividas where dividas.fk_user_id = ?1 and "
			+ "dividas.id = ?2", nativeQuery = true)
	Optional<Dividas>CustomfindByUser_idAndDebt_id(Long user_id,Long debt_id);
	
	@Transactional
	@Modifying
	@Query(value = "update dividas set valor=?2,destino=?3,data_pagamento = ?5,tipo_dividas_id=?6, paga = ?7"
			+ " where dividas.fk_user_id=?4 && dividas.id =?1", nativeQuery = true)
	void UpdateDebtByDto(Long debt_id,Double valor, String Destino, Long user_id, Date data_pagamento, Long recorrencia_id , boolean pag);

}
