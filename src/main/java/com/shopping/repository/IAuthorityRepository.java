package com.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopping.entity.Authority;
@Repository
public interface IAuthorityRepository extends JpaRepository<Authority, Integer>{

}
