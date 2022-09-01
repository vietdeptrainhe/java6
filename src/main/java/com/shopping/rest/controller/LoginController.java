package com.shopping.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.jwt.JwtTokenProvider;
import com.shopping.model.dto.request.LoginForm;
import com.shopping.model.dto.response.JwtResponse;
import com.shopping.security.userprincipal.UserPrinciple;

@RestController
@CrossOrigin("*")
//@RequestMapping("/security")
public class LoginController {
	
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenProvider tokenProvider;

	@PostMapping(value = "/login")
	public ResponseEntity<?> login(@RequestBody LoginForm loginForm) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginForm.getUsername(), loginForm.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token = tokenProvider.generateToken((UserPrinciple) authentication.getPrincipal());
		UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
		
		return ResponseEntity.ok(new JwtResponse(token, userPrinciple.getUsername(), userPrinciple.getAuthorities()));
	}
	
}
