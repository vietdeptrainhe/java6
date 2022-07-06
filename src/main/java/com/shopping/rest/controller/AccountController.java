package com.shopping.rest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.model.dto.AccountDto;
import com.shopping.service.impl.AccountServiceImpl;

@RestController
@RequestMapping("/accounts")
public class AccountController {
	
	@Autowired
	AccountServiceImpl accountServiceImpl;
	
	@GetMapping("")
	public ResponseEntity<?> getListAccount(@RequestParam(name = "keyword", required = false,defaultValue = "") String keyword ) {
		List<AccountDto> list = accountServiceImpl.findAllorSearch(keyword);
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	
	@GetMapping("/{username}")
	public ResponseEntity<?> getAccountByUsername(@PathVariable String username) {
		Optional<AccountDto> accountDto = accountServiceImpl.findByUsername(username);
//		return new ResponseEntity<>(accountDto,HttpStatus.OK);
		return ResponseEntity.ok(accountDto);
	}

	@PostMapping("")
	public String createAccount() {
		return null;
	}

	@PutMapping("/{id}")
	public String updateAccount() {
		return null;
	}
		
	@DeleteMapping("/{id}")
	public String deleteAccount() {
		return null;
	}
}
