package com.shopping.rest.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.repository.IAccountRepository;
import com.shopping.repository.IAuthorityRepository;
import com.shopping.repository.IRoleRepository;
import com.shopping.service.impl.AccountServiceImpl;
import com.shopping.service.impl.AuthorityServiceImpl;
import com.shopping.service.impl.RoleServiceImpl;

@RestController
@CrossOrigin("*")
public class AuthorityRestController {
	@Autowired
	AuthorityServiceImpl authorityServiceImpl;
	
	@Autowired
	RoleServiceImpl roleServiceImpl;
	
	@Autowired
	AccountServiceImpl accountServiceImpl;
	
	@GetMapping("/rest/authorities")
	public ResponseEntity<?> search2 () {
		Map<String, Object> data = new HashMap<>();
		data.put("authorities", authorityServiceImpl.findAll());
		data.put("roles", roleServiceImpl.findAll());
		data.put("accounts", accountServiceImpl.findAll());
		return new ResponseEntity<>(data,HttpStatus.OK);
		
	}
}
