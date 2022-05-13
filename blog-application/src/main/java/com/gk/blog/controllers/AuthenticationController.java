package com.gk.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gk.blog.exceptions.ApiException;
import com.gk.blog.payloads.JWTAuthRequest;
import com.gk.blog.payloads.JWTAuthResponse;
import com.gk.blog.payloads.UserDto;
import com.gk.blog.security.JWTTokenHelper;
import com.gk.blog.service.UserService;

@RestController
@RequestMapping("api/auth")
public class AuthenticationController {

	@Autowired
	private JWTTokenHelper jwtTokenHelper;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserService userService;

	@PostMapping("/login")
	public ResponseEntity<JWTAuthResponse> createToken(@RequestBody JWTAuthRequest request)
			throws UsernameNotFoundException {

		// 1. authenticating username and password
		this.authenticate(request.getUsername(), request.getPassword());

		// 2. getting user details from user name
		UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());

		// 3. generating token from user
		String generatedToken = jwtTokenHelper.generateToken(userDetails);

		// 4. return Token as JWTAuthResponse.
		return new ResponseEntity<JWTAuthResponse>(new JWTAuthResponse(generatedToken, userDetails.getUsername()),
				HttpStatus.OK);
	}

	private void authenticate(String username, String password) {
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,
				password);
		try {
			authenticationManager.authenticate(authenticationToken);
		} catch (BadCredentialsException e) {
			throw new ApiException("Invalid username or password");
		}
	}

	// register new user api

	@PostMapping("/register")
	public ResponseEntity<UserDto> registerUsers(@RequestBody UserDto userDto) {
		UserDto registerdUser = userService.registerNewUser(userDto);
		return new ResponseEntity<UserDto>(registerdUser, HttpStatus.CREATED);
	}
}
