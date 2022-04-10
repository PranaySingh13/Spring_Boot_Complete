package com.gk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gk.entity.User;
import com.gk.repository.UserRepository;

@Service
public class UserSeviceImplV2 implements UserService {

	@Autowired
	private UserRepository userRepo;

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "UserSeviceImplV2";
	}

	@Override
	public User createUser(User user) {
		User newUser = userRepo.save(user);
		return newUser;
	}

	@Override
	public List<User> getAllUsers() {
		List<User> users = userRepo.findAll();
		return users;
	}

	@Override
	public User getUserById(int id) {
		User user = userRepo.findById(id).get();
		return user;
	}

	@Override
	public User updateUser(User user) {

		User oldUser = userRepo.findById(user.getId()).get();
		oldUser.setEmail(user.getEmail());
		oldUser.setPassword(user.getPassword());
		User updateUser = userRepo.save(user);
		return updateUser;
	}

	@Override
	public void deleteUser(int id) {
		userRepo.deleteById(id);
	}

}
