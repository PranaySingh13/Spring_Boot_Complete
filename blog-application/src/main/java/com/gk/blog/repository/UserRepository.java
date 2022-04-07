package com.gk.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gk.blog.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

}
