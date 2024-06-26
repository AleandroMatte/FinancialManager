package com.aleandro.financial.UserWin.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.aleandro.financial.UserWin.Model.Winnings;
import com.aleandro.financial.shared.repository.BaseInterface;

public interface WinningRepository extends BaseInterface<Winnings> {

	
	
	@Query(value = "select * from recebimentos where recebimentos.fk_user_id = ?1", nativeQuery = true)
	List<Winnings> CustomfindByUser_id(Long user_id);
	
	@Query(value = "select * from recebimentos where recebimentos.fk_user_id = ?1 and "
			+ "recebimentos.id = ?2", nativeQuery = true)
	Optional<Winnings>CustomfindByUser_idAndWin_id(Long user_id,Long win_id);
	
	@Transactional
	@Modifying
	@Query(value = "update recebimentos set valor=?2,origem=?3,data_recebimento = ?5,tipo_recebimento_id=?6, recebida = ?7"
			+ " where recebimentos.fk_user_id=?4 && recebimentos.id =?1", nativeQuery = true)
	void UpdateWinByDto(Long win_id,Double valor, String Origem, Long user_id, Date data_recebimento, Long recorrencia_id , boolean recebido);
	
	@Transactional
	@Modifying
	@Query(value = "delete from recebimentos where recebimentos.fk_user_id=?1 && recebimentos.id =?2", nativeQuery = true)
	void CustomDeleteByIds(Long user_id,Long win_id);

	@Query(value = "select * from recebimentos where fk_user_id=:userId and data_recebimento Between :startDate and :endDate",
			nativeQuery=true)
	Optional<List<Winnings>> CustomFindByUserIdFilteredByDates(@Param("userId") Long userId, @Param("startDate") Date startDate, @Param("endDate") Date endDate);



}
