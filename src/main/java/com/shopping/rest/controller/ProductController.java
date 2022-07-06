package com.shopping.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.entity.Account;
import com.shopping.entity.Product;
import com.shopping.service.impl.AccountServiceImpl;
import com.shopping.service.impl.ProductServiceImpl;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	ProductServiceImpl productServiceImpl;
	
	@GetMapping("")
	public ResponseEntity<?> getListProduct() {
		List<Product> list = productServiceImpl.findAll();
		return new ResponseEntity<>(list,HttpStatus.OK);
	}

	@PostMapping("")
	public String createProduct() {
		System.out.println("viet");
		return null;
	}

	@PutMapping("/{id}")
	public String updateProduct() {
		return null;
	}
		
	@DeleteMapping("/{id}")
	public String deleteProduct() {
		return null;
	}
}
