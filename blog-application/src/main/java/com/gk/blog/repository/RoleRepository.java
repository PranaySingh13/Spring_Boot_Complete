package com.gk.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gk.blog.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

}
