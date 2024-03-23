package com.aleandro.financial.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.aleandro.financial.models.BaseModel;

@NoRepositoryBean
public interface BaseInterface<T extends BaseModel> extends JpaRepository<T, Long>{
	

}
