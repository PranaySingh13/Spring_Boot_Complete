package com.gk.controller;

import java.util.List;

import javax.validation.Valid;

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

import com.gk.dto.ApiResponse;
import com.gk.dto.UserDto;
import com.gk.exceptions.UserNotFoundException;
import com.gk.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService service;

	@PostMapping("/signup")
	public ResponseEntity<UserDto> saveUser(@Valid @RequestBody UserDto userDto) {
		return new ResponseEntity<UserDto>(service.saveUser(userDto), HttpStatus.CREATED);
	}

	@GetMapping("/fetchAll")
	public ResponseEntity<List<UserDto>> getAllUsers() {
		return ResponseEntity.ok(service.getAllUsers());
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserDto> getUser(@PathVariable int id) throws UserNotFoundException {
		return ResponseEntity.ok(service.getUser(id));
	}

	@PutMapping("/edit/{id}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable int id) {
		return new ResponseEntity<UserDto>(service.updateUser(userDto, id), HttpStatus.CREATED);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable int id) {
		service.deleteUser(id);
		return new ResponseEntity<ApiResponse>(new ApiResponse("User Deleted Successfully", true), HttpStatus.OK);
	}
}
