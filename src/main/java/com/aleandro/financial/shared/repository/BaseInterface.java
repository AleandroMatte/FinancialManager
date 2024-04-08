package com.aleandro.financial.shared.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.aleandro.financial.shared.model.BaseModel;

@NoRepositoryBean
public interface BaseInterface<T extends BaseModel> extends JpaRepository<T, Long>{
	

}
