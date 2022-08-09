package com.shopping.service;

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
public class AccountUserDetailsService implements UserDetailsService{

	@Autowired
	IAccountRepository IAccountRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Account> account = IAccountRepository.findByUsername(username);
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
