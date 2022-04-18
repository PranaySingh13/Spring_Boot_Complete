package com.gk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gk.entity.User;
import com.gk.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;

	public void addUser(User user) {
		userRepo.addUser(user);
	}

	// selecting single user
	public User getUser(int userId) {
		User user = userRepo.getUser(userId);
		return user;
	}

	// selecting list of users
	public List<User> getAllUsers() {
		List<User> users = userRepo.getAllUsers();
		return users;
	}

	public void updateUser(User user, int userId) {
		userRepo.updateUser(user, userId);
	}

	public void deleteUser(int userId) {
		userRepo.deleteUser(userId);
	}

}
