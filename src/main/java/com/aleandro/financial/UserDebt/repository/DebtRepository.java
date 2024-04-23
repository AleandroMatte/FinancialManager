package com.aleandro.financial.UserDebt.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.aleandro.financial.UserDebt.model.Debt;
import com.aleandro.financial.shared.repository.BaseInterface;

public interface DebtRepository  extends BaseInterface<Debt>{
	
	@Query(value = "select * from dividas where dividas.fk_user_id = ?1", nativeQuery = true)
	List<Debt> CustomfindByUser_id(UUID user_id);
	
	@Query(value = "select * from dividas where dividas.fk_user_id = ?1 and "
			+ "dividas.id = ?2", nativeQuery = true)
	Optional<Debt>CustomfindByUser_idAndDebt_id(UUID user_id,UUID debt_id);
	
	@Transactional
	@Modifying
	@Query(value = "update dividas set valor=?2,destino=?3,data_pagamento = ?5,tipo_dividas_id=?6, paga = ?7"
			+ " where dividas.fk_user_id=?4 && dividas.id =?1", nativeQuery = true)
	void UpdateDebtByDto(UUID debt_id,Double valor, String Destino, UUID user_id, Date data_pagamento, UUID recorrencia_id , boolean pag);
	
	@Transactional
	@Modifying
	@Query(value = "delete from dividas where dividas.fk_user_id=?1 && dividas.id =?2", nativeQuery = true)
	void CustomDeleteByIds(UUID user_id,UUID debt_id);

}
