package com.gk.service;

import java.util.List;

import com.gk.dto.UserDto;
import com.gk.exceptions.UserNotFoundException;

public interface UserService {
	
	UserDto saveUser(UserDto userDto);
	
	UserDto updateUser(UserDto userDto, int userId);
	
	List<UserDto> getAllUsers();
	
	UserDto getUser(int id) throws UserNotFoundException;
	
	void deleteUser(Integer userId);

}
