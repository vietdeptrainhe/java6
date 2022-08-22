package com.shopping.model.dto.response;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {
	String token;
	private String type = "Bearer";
	private String name;
	private Collection<? extends GrantedAuthority> role;
	public JwtResponse(String token, String name, Collection<? extends GrantedAuthority> role) {
		super();
		this.token = token;
		this.name = name;
		this.role = role;
	}
	
}
