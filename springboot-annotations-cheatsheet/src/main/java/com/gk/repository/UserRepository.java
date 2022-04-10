package com.gk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gk.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

}
