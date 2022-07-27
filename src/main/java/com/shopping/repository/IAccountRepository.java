package com.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopping.entity.Account;
import com.shopping.model.dto.AccountDto;

@Repository
public interface IAccountRepository extends JpaRepository<Account, String>{
	
}
