package com.gk.blog.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gk.blog.config.AppConstants;
import com.gk.blog.entity.Role;
import com.gk.blog.entity.User;
import com.gk.blog.exceptions.ResourceNotFoundException;
import com.gk.blog.payloads.UserDto;
import com.gk.blog.repository.RoleRepository;
import com.gk.blog.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepository roleRepository;

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

		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		// encode the password
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		user.setAbout(userDto.getAbout());

		User updateUser = userRepo.save(user);

		return userToUserDto(updateUser);

	}

	@Override
	public UserDto deleteUser(Integer userId) throws ResourceNotFoundException {
		User user = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "user id", userId));
		userRepo.delete(user);
		return modelMapper.map(user, UserDto.class);
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
		User user = modelMapper.map(userDto, User.class);

		// Not needed any more
//		user.setUserId(userDto.getUserId());
//		user.setUserName(userDto.getUserName());
//		user.setUserEmail(userDto.getUserEmail());
//		user.setUserPassword(userDto.getUserPassword());
//		user.setAbout(userDto.getAbout());

		return user;
	}

	/*
	 * For Response we need the DTO objects but we are getting response in Entities
	 * from JPA repository methods. THerefore We will convert User to UserDto Object
	 * again.
	 */
	private UserDto userToUserDto(User user) {
		UserDto userDto = modelMapper.map(user, UserDto.class);
		return userDto;
	}

	@Override
	public UserDto registerNewUser(UserDto userDto) {
		User user = modelMapper.map(userDto, User.class);

		// encode the password
		user.setPassword(passwordEncoder.encode(user.getPassword()));

		// roles
		Role role = roleRepository.findById(AppConstants.ROLE_USER).get();

		user.getRoles().add(role);

		User newUser = userRepo.save(user);
		System.out.println(newUser.getUsername());
		return modelMapper.map(newUser, UserDto.class);
	}

}
