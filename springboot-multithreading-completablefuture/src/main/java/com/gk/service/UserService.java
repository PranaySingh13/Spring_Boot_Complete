package com.gk.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.gk.entity.User;
import com.gk.repo.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;

	Logger logger = org.slf4j.LoggerFactory.getLogger(UserService.class);

	@Async // Annotation that marks a method as a candidate for asynchronous execution.
	public CompletableFuture<List<User>> saveUsers(MultipartFile file) throws Exception {
		long startTime = System.currentTimeMillis();
		List<User> users = parseCSVFile(file);
		logger.info("Saving List of users of size {}", users.size(), "" + Thread.currentThread().getName());
		List<User> savedUsers = repo.saveAll(users);
		long endTime = System.currentTimeMillis();
		logger.info("Total Time {} ", (endTime - startTime));
		return CompletableFuture.completedFuture(savedUsers);
	}

	@Async
	public CompletableFuture<List<User>> findAllUsers() {
		logger.info("Get List Of User by: " + Thread.currentThread().getName());
		List<User> users = repo.findAll();
		return CompletableFuture.completedFuture(users);
	}

	public List<User> parseCSVFile(final MultipartFile file) throws Exception {
		final List<User> users = new ArrayList<>();
		try {
			final BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()));
			String line;
			while ((line = br.readLine()) != null) {
				final String[] data = line.split(",");
				final User user = new User();
				user.setName(data[0]);
				user.setEmail(data[1]);
				user.setGender(data[2]);
				users.add(user);
			}
			return users;
		} catch (final IOException e) {
			logger.error("Failed to parse CSV file {}", e);
			throw new Exception("Failed to parse CSV file {}", e);
		}

	}
}
