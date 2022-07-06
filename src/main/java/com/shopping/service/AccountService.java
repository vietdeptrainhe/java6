package com.shopping.service;

import java.util.List;
import java.util.Optional;

import com.shopping.entity.Account;
import com.shopping.model.dto.AccountDto;

public interface AccountService {
	List<AccountDto> findAllorSearch(String keyword);		
	Optional<AccountDto> findByUsername(String username);
	Account save(Account account);
	void delete(String username);
}
