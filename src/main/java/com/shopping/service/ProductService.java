package com.shopping.service;

import java.util.List;
import java.util.Optional;

import com.shopping.entity.Product;


public interface ProductService {
	List<Product> findAll();		
	Optional<Product> findById(Integer id);
	Product save(Product product);
	void delete(Integer id);
}
