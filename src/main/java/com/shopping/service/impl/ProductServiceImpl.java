package com.shopping.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.entity.Product;
import com.shopping.repository.IProductRepository;
import com.shopping.service.ProductService;
@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	IProductRepository iProductRepository;

	@Override
	public List<Product> findAll() {
		// TODO Auto-generated method stub
		return iProductRepository.findAll();
	}


	@Override
	public Product save(Product product) {
		// TODO Auto-generated method stub
		return iProductRepository.save(product);
	}

	@Override
	public void delete(Integer id) {
		iProductRepository.deleteById(id);
	}


	@Override
	public Optional<Product> findById(Integer id) {
		return iProductRepository.findById(id);
	}
	

}
