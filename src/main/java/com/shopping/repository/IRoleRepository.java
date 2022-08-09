package com.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopping.entity.Role;
@Repository
public interface IRoleRepository extends JpaRepository<Role, String>{

}
