package com.gk.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gk.dto.UserDto;
import com.gk.entity.User;
import com.gk.exceptions.UserNotFoundException;
import com.gk.repo.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repo;

	@Autowired
	private ModelMapper mapper;

	@Override
	public UserDto saveUser(UserDto userDto) {
		User user = mapper.map(userDto, User.class);
		User createdUser = repo.save(user);
		return mapper.map(createdUser, UserDto.class);
	}

	@Override
	public UserDto updateUser(UserDto userDto, int userId) {
		User user = repo.findByUserId(userId);
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setGender(userDto.getGender());
		user.setMobile(userDto.getMobile());
		user.setAge(userDto.getAge());
		user.setNationality(userDto.getNationality());

		User updatedUser = repo.save(user);

		return mapper.map(updatedUser, UserDto.class);

	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> users = repo.findAll();
		return users.stream().map(user -> mapper.map(user, UserDto.class)).collect(Collectors.toList());
	}

	@Override
	public UserDto getUser(int id) throws UserNotFoundException {
		/*
		 * This is functioning of Spring Data JPA first just add findBy and second
		 * append entity field in java camelCase notation in method name so that JPA
		 * understands this method irrespective of applying @Query in repository
		 * interface method created to select user based on this field.
		 * 
		 * Ex:- findByUserId, findByEmail etc.
		 */
		User user = repo.findByUserId(id);
		if (user != null)
			return mapper.map(user, UserDto.class);
		else
			throw new UserNotFoundException("User Not Found with id: " + id);
	}

	@Override
	public void deleteUser(Integer userId) {
		repo.deleteById(userId);// Deletes the entity with the given id.
	}

}
