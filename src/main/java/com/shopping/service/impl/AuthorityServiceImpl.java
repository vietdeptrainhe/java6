package com.shopping.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.entity.Account;
import com.shopping.entity.Authority;
import com.shopping.model.dto.AccountDto;
import com.shopping.model.dto.AuthorityDto;
import com.shopping.model.mapper.AuthorityMapper;
import com.shopping.repository.IAuthorityRepository;
import com.shopping.service.AuthorityService;

@Service
public class AuthorityServiceImpl implements AuthorityService{

	@Autowired
	IAuthorityRepository iAuthorityRepository;
	
	@Override
	public List<AuthorityDto> findAll() {
		List<AuthorityDto> authorityDtos = new ArrayList<AuthorityDto>();
		List<Authority> authorities = iAuthorityRepository.findAll();
		for (Authority authority : authorities) {
			authorityDtos.add(AuthorityMapper.toAuthorityDto(authority));
		}
		return authorityDtos;
	}

}
