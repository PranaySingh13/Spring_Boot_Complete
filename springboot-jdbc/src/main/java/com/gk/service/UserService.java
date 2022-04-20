package com.gk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gk.entity.User;
import com.gk.repository.UserRepositoryV1;
import com.gk.repository.UserRepositoryV2;

@Service
public class UserService {

	@Autowired
	private UserRepositoryV1 userRepov1;

	@Autowired
	private UserRepositoryV2 userRepov2;

	public void addUser(User user) {
		userRepov1.addUser(user);
	}

	public String addUsers(List<User> users) {
		return userRepov1.addUsers(users);
	}

	// selecting single user
	public User getUser(int userId) {
		User user = userRepov1.getUser(userId);
		return user;
	}

	// selecting list of users
	public List<User> getAllUsers() {
		List<User> users = userRepov2.getAllUsers();
		return users;
	}

	public void updateUser(User user, int userId) {
		userRepov1.updateUser(user, userId);
	}

	public void deleteUser(int userId) {
		userRepov1.deleteUser(userId);
	}

	public String getUserName(int userId) {
		return userRepov2.getUserName(userId);
	}

}
