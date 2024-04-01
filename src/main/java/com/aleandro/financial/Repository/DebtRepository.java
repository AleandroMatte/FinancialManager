package com.aleandro.financial.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.aleandro.financial.models.Dividas;

public interface DebtRepository  extends BaseInterface<Dividas>{
	
	@Query(value = "select * from dividas where dividas.fk_user_id = ?1", nativeQuery = true)
	List<Dividas> CustomfindByUser_id(Long user_id);

}
