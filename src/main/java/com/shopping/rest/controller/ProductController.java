package com.shopping.rest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.entity.Account;
import com.shopping.entity.Product;
import com.shopping.service.impl.AccountServiceImpl;
import com.shopping.service.impl.ProductServiceImpl;

@RestController
@CrossOrigin("*")
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	ProductServiceImpl productServiceImpl;
	
	@GetMapping("")
	public ResponseEntity<?> getListProduct() {
		List<Product> list = productServiceImpl.findAll();
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getOneProduct(@PathVariable Integer id) {
		Optional<Product> product = productServiceImpl.findById(id);
		return new ResponseEntity<>(product,HttpStatus.OK);
	}

	@PostMapping("")
	public Product createProduct(@RequestBody Product product) {
		return productServiceImpl.save(product);
	}

	@PutMapping()
	public Product updateProduct(@RequestBody Product product) {
		return productServiceImpl.save(product);
	}
		
	@DeleteMapping("/{id}")
	public void deleteProduct(@PathVariable Integer id) {
		productServiceImpl.delete(id);
	}
}
