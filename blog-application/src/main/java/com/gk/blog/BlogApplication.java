package com.gk.blog;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.gk.blog.config.AppConstants;
import com.gk.blog.entity.Role;
import com.gk.blog.repository.RoleRepository;

@SpringBootApplication
public class BlogApplication implements CommandLineRunner {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(passwordEncoder.encode("Abc@123xyz"));

		// Inserting roles in role table. If already present, it will override with Ids
		try {
			Role role1 = new Role();
			role1.setId(AppConstants.ROLE_ADMIN);
			role1.setName("ADMIN");

			Role role2 = new Role();
			role2.setId(AppConstants.ROLE_USER);
			role2.setName("USER");

			List<Role> list = new ArrayList<>();
			list.add(role1);
			list.add(role2);
			List<Role> result = roleRepository.saveAll(list);
			result.forEach(role -> System.out.println(role));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
