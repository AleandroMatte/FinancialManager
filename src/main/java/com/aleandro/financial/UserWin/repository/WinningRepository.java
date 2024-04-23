package com.aleandro.financial.UserWin.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.aleandro.financial.UserWin.Model.Winnings;
import com.aleandro.financial.shared.repository.BaseInterface;

public interface WinningRepository extends BaseInterface<Winnings> {

	
	
	@Query(value = "select * from recebimentos where recebimentos.fk_user_id = ?1", nativeQuery = true)
	List<Winnings> CustomfindByUser_id(UUID user_id);
	
	@Query(value = "select * from recebimentos where recebimentos.fk_user_id = ?1 and "
			+ "recebimentos.id = ?2", nativeQuery = true)
	Optional<Winnings>CustomfindByUser_idAndDebt_id(UUID user_id,UUID win_id);
	
	@Transactional
	@Modifying
	@Query(value = "update recebimentos set valor=?2,origem=?3,data_recebimento = ?5,tipo_recebimento_id=?6, recebida = ?7"
			+ " where recebimentos.fk_user_id=?4 && recebimentos.id =?1", nativeQuery = true)
	void UpdateWinByDto(UUID win_id,Double valor, String Origem, UUID user_id, Date data_recebimento, UUID recorrencia_id , boolean recebido);
	
	@Transactional
	@Modifying
	@Query(value = "delete from recebimentos where recebimentos.fk_user_id=?1 && recebimentos.id =?2", nativeQuery = true)
	void CustomDeleteByIds(UUID user_id,UUID win_id);



}
