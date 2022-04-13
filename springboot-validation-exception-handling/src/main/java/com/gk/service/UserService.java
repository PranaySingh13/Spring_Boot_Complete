package com.gk.service;

import java.util.List;

import com.gk.dto.UserDto;

public interface UserService {
	
	UserDto saveUser(UserDto userDto);
	
	UserDto updateUser(UserDto userDto, int userId);
	
	List<UserDto> getAllUsers();
	
	UserDto getUser(int id);
	
	void deleteUser(Integer userId);

}
