package com.shopping.model.dto;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shopping.entity.Authority;
import com.shopping.entity.Order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {
	String username;
	String fullname;
	String email;
	String photo;
	@JsonIgnore
	List<Order> orders;
	@JsonIgnore
	List<Authority> authorities;
}
