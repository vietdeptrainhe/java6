package com.shopping.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shopping.entity.Account;
import com.shopping.model.dto.AccountDto;
import com.shopping.model.mapper.AccountMapper;
import com.shopping.repository.IAccountRepository;
import com.shopping.service.AccountService;
@Component
public class AccountServiceImpl implements AccountService{
	@Autowired
	IAccountRepository iAccountRepository;

	@Override
	public List<AccountDto> findAll() {
		List<AccountDto> accountDtos = new ArrayList<AccountDto>();
		List<Account> accounts = iAccountRepository.findAll();
		for (Account account : accounts) {
			accountDtos.add(AccountMapper.toAccountDto(account));
		}
		return accountDtos;
	}

	@Override
	public Optional<AccountDto> findById(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account save(AccountDto account) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(String username) {
		// TODO Auto-generated method stub
		
	}
	
//	@Override
//	public List<Account> findAll() {
//		return iAccountRepository.findAll();
//	}
//
//	@Override
//	public Optional<Account> findById(String username) {
//		return iAccountRepository.findById(username);
//	}
//
//	@Override
//	public Account save(Account account) {
//		return iAccountRepository.save(account);
//	}
//
//	@Override
//	public void delete(String username) {
//		iAccountRepository.deleteById(username);
//	}

}
