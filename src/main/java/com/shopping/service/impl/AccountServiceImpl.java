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
	
//	@Override
//	public List<AccountDto> findAllorSearch() {
//		List<Account> list = iAccountRepository.findAll();
//		List<AccountDto> result = new ArrayList<AccountDto>();
//		for (Account account : list) {
//			result.add(AccountMapper.toAccountDto(account));
//		}
//		return result;
//	}
	@Override
	public List<AccountDto> findAllorSearch(String keyword) {
		List<Account> list = iAccountRepository.findAll();
		List<AccountDto> result = new ArrayList<>();
		for (Account account : list) {
			if (account.getUsername().contains(keyword)) {
			 result.add(AccountMapper.toAccountDto(account));				
			}
		}
		return result;
	}

	@Override
	public Account save(Account account) {
		return iAccountRepository.save(account);
	}

	@Override
	public void delete(String username) {
		iAccountRepository.deleteById(username);
	}

	@Override
	public Optional<AccountDto> findByUsername(String username) {
		Optional<Account> result = iAccountRepository.findById(username);
		AccountDto accountDto = null;
		if (result.isPresent()) {
			accountDto = AccountMapper.toAccountDto(result.get());
		}
		return Optional.ofNullable(accountDto);
	}

	


}
