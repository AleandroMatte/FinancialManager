package com.aleandro.financial.shared.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aleandro.financial.shared.model.BaseModel;

@Repository
public abstract interface BaseInterface<T extends BaseModel> extends JpaRepository<T, UUID>{
	

}
