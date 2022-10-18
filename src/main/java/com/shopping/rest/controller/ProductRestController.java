package com.shopping.rest.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
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
import com.shopping.repository.IProductRepository;
import com.shopping.service.impl.AccountServiceImpl;
import com.shopping.service.impl.ProductServiceImpl;

@RestController
@CrossOrigin("*")
@RequestMapping("/products")
public class ProductRestController {
	
	@Autowired
	ProductServiceImpl productServiceImpl;
	
	@Autowired
	IProductRepository iProductRepository;
	
	@GetMapping("")
	public ResponseEntity<?> getListProduct() {
		List<Product> list = productServiceImpl.findAll();
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	
	@GetMapping("/search")
	public ResponseEntity<?> search(@RequestParam(name = "name", required = false) String name, HttpServletRequest request) {
//		List<Product> list = null;
//		if (StringUtils.hasText(name)) {
//			list = productServiceImpl.findByNameContaining(name);
//		}else {
//			list = productServiceImpl.findAll();
//		}
		String abc = request.getSession().getServletContext().getRealPath("/");
		return new ResponseEntity<>(abc,HttpStatus.OK);
//				new ResponseEntity<>(list,HttpStatus.OK);
	}
	
	@GetMapping("/views")
	public ResponseEntity<?> search2(
			@RequestParam(name = "name", required = false) String name,
			@RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size			
			) {
		
		int currentPage = page.orElse(0);
		int pageSize = size.orElse(9);
		
		Pageable pageable = PageRequest.of(currentPage, pageSize);		
		
		Page<Product> resultPage = null;
		
//		List<Product> list = null;
		if (StringUtils.hasText(name)) {
			resultPage = productServiceImpl.findByNameContaining(name,pageable);
		}else {
			resultPage = iProductRepository.findAll(pageable);
		}
		return new ResponseEntity<>(resultPage,HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getOneProduct(@PathVariable Integer id) {
		Optional<Product> product = productServiceImpl.findById(id);
		return new ResponseEntity<>(product,HttpStatus.OK);
	}

	@PostMapping()
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
