package com.gk.blog.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//Dto is class for data transfer & which needed to 'show' on web page.

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

	private int userId;
	private String userName;
	private String userEmail;
	private String userPassword;
	private String about;

}
