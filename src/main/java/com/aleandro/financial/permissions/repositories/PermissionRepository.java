package com.aleandro.financial.permissions.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aleandro.financial.permissions.infra.models.Permissions;

@Repository
public interface PermissionRepository extends JpaRepository<Permissions, Long>{





}

