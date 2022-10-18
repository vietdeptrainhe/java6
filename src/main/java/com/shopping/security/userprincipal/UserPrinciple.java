package com.shopping.security.userprincipal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.shopping.entity.Account;
import com.shopping.entity.Authority;
import com.shopping.repository.IAccountRepository;

public class UserPrinciple implements UserDetails{

	private Account account;
	
	public UserPrinciple(Account account) {
		this.account = account;
	}
	
	@Autowired
	IAccountRepository iAccountRepository;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
		String[] roles = account.getAuthorities().stream()
				.map(au -> au.getRole().getName())
				.collect(Collectors.toList()).toArray(new String[0]);
		for (String string : roles) {
			grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_"+string));	
			System.out.println(string);
		}
		return grantedAuthorities;
	}

	@Override
	public String getPassword() {
		return account.getPassword();
	}

	@Override
	public String getUsername() {
		return account.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
