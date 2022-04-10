package com.gk.service;

import java.util.List;

import com.gk.entity.User;

public interface UserService {

	User createUser(User user);

	List<User> getAllUsers();

	User getUserById(int id);

	User updateUser(User user);

	void deleteUser(int id);
	
	String getName();

}
