package com.shopping.service;

import java.util.List;
import java.util.Optional;

import com.shopping.entity.Account;

public interface AccountService {
	List<Account> findAll();		
	Optional<Account> findById(String username);
	Account save(Account account);
	void delete(String username);
}
