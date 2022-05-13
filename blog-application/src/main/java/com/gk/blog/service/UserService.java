package com.gk.blog.service;

import java.util.List;

import com.gk.blog.exceptions.ResourceNotFoundException;
import com.gk.blog.payloads.UserDto;

public interface UserService {

	UserDto registerNewUser(UserDto userDto);

	UserDto createUser(UserDto user);

	UserDto getUserById(Integer userId) throws ResourceNotFoundException;

	UserDto updateUser(UserDto user, Integer userId) throws ResourceNotFoundException;

	UserDto deleteUser(Integer userId) throws ResourceNotFoundException;

	List<UserDto> getAllUsers();

}
