package com.shopping.security.userprincipal;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.shopping.entity.Account;
import com.shopping.repository.IAccountRepository;

@Service
public class UserDetailService implements UserDetailsService{

	@Autowired
	IAccountRepository iAccountRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Account> account = iAccountRepository.findByUsername(username);
		
		return new UserPrinciple(account.get());
	}

}
