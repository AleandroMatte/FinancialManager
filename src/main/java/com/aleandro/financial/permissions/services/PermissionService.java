package com.aleandro.financial.permissions.services;

import java.util.HashMap;
import java.util.List;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aleandro.financial.permissions.infra.models.Permissions;
import com.aleandro.financial.permissions.repositories.PermissionRepository;

@Service
public class PermissionService {
	
	@Autowired
	private PermissionRepository permission_repo;

	public PermissionService() {
		// TODO Auto-generated constructor stub
	}
	
	public HashMap<String, Permissions> get_permissions_by_description(){
		List<Permissions> permissions_list = permission_repo.findAll();
		HashMap<String, Permissions> permission_dict = new HashMap<>();
		for (Permissions permission : permissions_list) {
			permission_dict.put(permission.getDescription(), permission);	
		}
		return permission_dict;
	}

}
