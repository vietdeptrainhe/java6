package com.shopping.model.dto;

import com.shopping.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorityDto {
	private Integer id;
	private AccountDto accountdto;
	private Role role;
}
