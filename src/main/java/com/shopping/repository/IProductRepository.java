package com.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopping.entity.Product;
@Repository
public interface IProductRepository extends JpaRepository<Product, Integer>{

}
