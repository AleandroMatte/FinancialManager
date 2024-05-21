package com.aleandro.financial.UserSec.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.aleandro.financial.UserSec.infra.models.UserSecModel;

@Repository
public interface UserSecRepository extends JpaRepository<UserSecModel, Long>{
	


	@Query(value = "SELECT * FROM users WHERE user_name =:user_name", nativeQuery = true)
	public Optional<UserSecModel>findByUsername(@Param("user_name") String user_name);

	
	
	
}
