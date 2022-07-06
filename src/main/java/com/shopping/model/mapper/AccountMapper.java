package com.shopping.model.mapper;

import com.shopping.entity.Account;
import com.shopping.model.dto.AccountDto;

public class AccountMapper {
	public static AccountDto toAccountDto(Account account) {
		AccountDto adto = new AccountDto();
		adto.setUsername(account.getUsername());
		adto.setFullname(account.getFullname());
		adto.setEmail(account.getEmail());
		adto.setPhoto(account.getPhoto());
		adto.setOrders(account.getOrders());
		adto.setAuthorities(account.getAuthorities());
		return adto;
		
	}
}
