package com.shopping.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.shopping.entity.Account;
import com.shopping.repository.IAccountRepository;

@Service
public class AccountDetailServiceImpl implements UserDetailsService{

	@Autowired
	IAccountRepository iAccountRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		Optional<Account> account = iAccountRepository.findByUsername(username);
//		return new User(account.get().getUsername()
//				,account.get().getPassword()
//				,new ArrayList<>());
		Optional<Account> account = iAccountRepository.findByUsername(username);
		if (account == null) {
			throw new UsernameNotFoundException("Not find user");
		}
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(account.get().getAuthorities().toString()));
		
		UserDetails userDetails = new User(
				account.get().getUsername(),
				account.get().getPassword(),
				authorities
				);
		return userDetails;
	}

}
