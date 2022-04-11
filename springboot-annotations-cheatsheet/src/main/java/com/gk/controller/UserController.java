package com.gk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gk.LazyLoadingBean;
import com.gk.config.BeanConfig;
import com.gk.config.ConfigProperties;
import com.gk.entity.User;
import com.gk.payloads.ApiResponse;
import com.gk.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	// @Qualifier("UserSeviceImplV2")
	private UserService userService;

	@Autowired
	private LazyLoadingBean lazyLoadingBean;

	@Autowired
	private BeanConfig config;

	@Autowired
	private ConfigProperties configProperties;

	@GetMapping("/")
	public ResponseEntity<String> testingBean() {

		System.out.println(configProperties);// ConfigProperties(HOST=test@gk.com, USERNAME=test@gk.com, PORT=8080)

		return new ResponseEntity<String>(userService.getName(), HttpStatus.OK);
	}

	@PostMapping("/create")
	public ResponseEntity<User> create(@RequestBody User user) {
		return new ResponseEntity<User>(userService.createUser(user), HttpStatus.CREATED);
	}

	@GetMapping("/all")
	public ResponseEntity<List<User>> getAllUsers() {
		return new ResponseEntity<List<User>>(userService.getAllUsers(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> getUserById(@PathVariable int id) {
		return new ResponseEntity<User>(userService.getUserById(id), HttpStatus.OK);
	}

	@PutMapping("/")
	public ResponseEntity<User> updateUser(@RequestBody User user) {
		return new ResponseEntity<User>(userService.updateUser(user), HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteUserById(@PathVariable int id) {
		userService.deleteUser(id);
		return new ResponseEntity<ApiResponse>(new ApiResponse("User Deleted Successfully", true), HttpStatus.OK);
	}
}
