package com.shopping.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.entity.Role;
import com.shopping.repository.IRoleRepository;
import com.shopping.service.RoleService;
@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	IRoleRepository iRoleRepository;
	
	@Override
	public List<Role> findAll() {
		return iRoleRepository.findAll();
	}

}
