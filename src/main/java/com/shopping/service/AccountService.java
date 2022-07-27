package com.shopping.service;

import java.util.List;
import java.util.Optional;

import com.shopping.entity.Account;
import com.shopping.model.dto.AccountDto;

public interface AccountService {
	List<AccountDto> findAll();		
	Optional<AccountDto> findById(String username);
	Account save(AccountDto account);
	void delete(String username);
}
