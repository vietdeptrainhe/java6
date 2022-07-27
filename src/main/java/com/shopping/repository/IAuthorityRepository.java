package com.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopping.entity.Authority;

public interface IAuthorityRepository extends JpaRepository<Authority, Integer>{

}
