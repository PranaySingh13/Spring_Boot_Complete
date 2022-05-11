package com.gk.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gk.blog.entity.User;
import com.gk.blog.exceptions.UserNotfoundExcxeption;
import com.gk.blog.payloads.JWTAuthRequest;
import com.gk.blog.payloads.JWTAuthResponse;
import com.gk.blog.security.CustomUserDetailService;
import com.gk.blog.security.JWTTokenHelper;

@RestController
@RequestMapping("api/auth")
public class AuthenticationController {

	@Autowired
	private JWTTokenHelper jwtTokenHelper;

	@Autowired
	private CustomUserDetailService customUserDetailService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/login")
	public ResponseEntity<JWTAuthResponse> createToken(@RequestBody JWTAuthRequest request)
			throws UserNotfoundExcxeption {

		// 1. authenticating username and password
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

		// 2. getting user details from user name
		User user = customUserDetailService.loadUserByUsername(request.getUsername());

		// 3. generating token from user
		String generatedToken = jwtTokenHelper.generateToken(user);

		// 4. return Token as JWTAuthResponse.
		return new ResponseEntity<JWTAuthResponse>(new JWTAuthResponse(generatedToken, user.getUsername()),
				HttpStatus.OK);
	}
}
