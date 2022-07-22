package com.shopping.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;

import com.shopping.entity.Product;
import com.shopping.repository.IProductRepository;
import com.shopping.service.ProductService;
@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	IProductRepository iProductRepository;

	@Override
	public List<Product> findAll() {
		return iProductRepository.findAll();
	}


	@Override
	public Product save(Product product) {
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


	@Override
	public Page<Product> findByRequired(
			@RequestParam(name = "name", required = false)String name, 
			@RequestParam(name = "page")Optional<Integer> page,
			@RequestParam(name = "size")Optional<Integer> size) {
		int currentPage = page.orElse(0);
		int pageSize = page.orElse(9);
		Pageable pageable = PageRequest.of(currentPage, pageSize);
		
		Page<Product> resultPage = null;
		
		if (StringUtils.hasText(name)) {
			resultPage = iProductRepository.findByNameContaining(name, pageable);
		}else {
			resultPage = iProductRepository.findAll(pageable);
		}
		
		return resultPage;
	}


	@Override
	public Page<Product> findByNameContaining(String name, Pageable page) {
		
		return null;
	}


//	@Override
//	public Page<Product> findByNameContaining(String name, Pageable pageable) {
//		return iProductRepository.findByNameContaining(name);
//	} 

}
