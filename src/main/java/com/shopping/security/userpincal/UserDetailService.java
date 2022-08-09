package com.shopping.security.userpincal;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.shopping.entity.Account;
import com.shopping.repository.IAccountRepository;

public class UserDetailService implements UserDetailsService{

	@Autowired
	private IAccountRepository iAccountRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account account = iAccountRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found -> user name or password "+username ));	
		if (account == null) {
			throw new UsernameNotFoundException("not foud user exception");
		}
		return new UserPrinciple(account);
	}

}
