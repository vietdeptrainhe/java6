package com.shopping.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopping.entity.Account;
import com.shopping.model.dto.AccountDto;

@Repository
public interface IAccountRepository extends JpaRepository<Account, String>{
	Optional<Account> findByUsername(String username);
}
