package com.bvm.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bvm.security.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

	
}
