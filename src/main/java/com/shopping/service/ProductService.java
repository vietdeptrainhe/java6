package com.shopping.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.shopping.entity.Product;


public interface ProductService {
	List<Product> findAll();
	Page<Product> findByNameContaining(String name, Pageable page);
	Page<Product> findByRequired(String name, Optional<Integer> page, Optional<Integer> size);		
	Optional<Product> findById(Integer id);
	Product save(Product product);
	void delete(Integer id);
}
