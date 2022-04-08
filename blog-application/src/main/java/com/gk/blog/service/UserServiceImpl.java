package com.gk.blog.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gk.blog.entity.User;
import com.gk.blog.exceptions.ResourceNotFoundException;
import com.gk.blog.payloads.UserDto;
import com.gk.blog.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;

	@Override
	public UserDto createUser(UserDto userDto) {
		User user = userDtoToUser(userDto);
		User savedUser = userRepo.save(user);
		return userToUserDto(savedUser);
	}

	@Override
	public UserDto getUserById(Integer userId) throws ResourceNotFoundException {

		User user = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "user Id", userId));
		return userToUserDto(user);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) throws ResourceNotFoundException {
		User user = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "user Id", userId));

		user.setUserName(userDto.getUserName());
		user.setUserEmail(userDto.getUserEmail());
		user.setUserPassword(userDto.getUserPassword());
		user.setAbout(userDto.getAbout());

		User updateUser = userRepo.save(user);

		return userToUserDto(updateUser);

	}

	@Override
	public void deleteUser(Integer userId) {
		// User user=userRepo.findById(userId).orElseThrow(()->new
		// ResourceNotFoundException("User", "user id", userId));
		userRepo.deleteById(userId);// Deletes the entity with the given id.
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> users = userRepo.findAll();
		return users.stream().map(user -> userToUserDto(user)).collect(Collectors.toList());
	}

	/*
	 * For Calling JPA repository methods we need entities object but we are getting
	 * DTO objects. Therefore We will convert UserDto to User Object.
	 */

	private User userDtoToUser(UserDto userDto) {
		User user = new User();
		user.setUserId(userDto.getUserId());
		user.setUserName(userDto.getUserName());
		user.setUserEmail(userDto.getUserEmail());
		user.setUserPassword(userDto.getUserPassword());
		user.setAbout(userDto.getAbout());

		return user;
	}

	/*
	 * For Response we need the DTO objects but we are getting response in Entities
	 * from JPA repository methods. THerefore We will convert User to UserDto Object
	 * again.
	 */
	private UserDto userToUserDto(User user) {
		UserDto userDto = new UserDto();
		userDto.setUserId(user.getUserId());
		userDto.setUserName(user.getUserName());
		userDto.setUserEmail(user.getUserEmail());
		userDto.setUserPassword(user.getUserPassword());
		userDto.setAbout(user.getAbout());

		return userDto;
	}

}
