package com.gk.blog.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.gk.blog.entity.User;
import com.gk.blog.exceptions.UserNotfoundExcxeption;
import com.gk.blog.repository.UserRepository;

@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private UserRepository userRepo;

	@Override
	public User loadUserByUsername(String username) {
		// loading user from database by username
		User user = null;
		try {
			user = userRepo.findByUserEmail(username)
					.orElseThrow(() -> new UserNotfoundExcxeption("User", "email", username));
		} catch (UserNotfoundExcxeption e) {
			e.printStackTrace();
		}

		return user;
	}

}
