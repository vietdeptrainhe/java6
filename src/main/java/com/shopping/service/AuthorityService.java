package com.shopping.service;

import java.util.List;

import com.shopping.model.dto.AuthorityDto;

public interface AuthorityService {
	List<AuthorityDto> findAll();		
//	Optional<AccountDto> findById(String username);
//	Account save(AccountDto account);
//	void delete(String username);
}
