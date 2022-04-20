package com.gk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gk.entity.User;
import com.gk.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/")
	private void addUser(@RequestBody User user) {
		userService.addUser(user);
	}

	@PutMapping("/{userId}")
	private void updateUser(@RequestBody User user, @PathVariable int userId) {
		userService.updateUser(user, userId);
	}

	@GetMapping("/{userId}")
	public User getUser(@PathVariable int userId) {
		User user = userService.getUser(userId);
		return user;
	}

	@GetMapping("/all")
	public List<User> getAllUsers() {
		List<User> users = userService.getAllUsers();
		return users;
	}

	@DeleteMapping("/{userId}")
	public void deleteUser(@PathVariable int userId) {
		userService.deleteUser(userId);
	}

	@GetMapping("/name/{userId}")
	public String getUserName(@PathVariable int userId) {
		String name = userService.getUserName(userId);
		return name;
	}
}
