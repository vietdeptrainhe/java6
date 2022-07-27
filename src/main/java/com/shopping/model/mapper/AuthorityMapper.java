package com.shopping.model.mapper;

import com.shopping.entity.Authority;
import com.shopping.model.dto.AuthorityDto;

public class AuthorityMapper {
	public static AuthorityDto toAuthorityDto(Authority authority) {
		AuthorityDto authorityDto = new AuthorityDto();
		authorityDto.setId(authority.getId());
		authorityDto.setAccountdto(AccountMapper.toAccountDto(authority.getAccount()));
		authorityDto.setRole(authority.getRole());
		return authorityDto;
	}
}
