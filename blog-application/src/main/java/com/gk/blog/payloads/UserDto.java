package com.gk.blog.payloads;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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

	private int id;
	
	@NotBlank(message = "Name can not be blank !")
	@Size(min = 4, message = "Name must be minimum of 4 characters !")
	private String name;
	
	@Email(message = "Email is not Valid !")
	@NotBlank(message = "Email can not be blank !")
	private String email;
	
	@NotBlank(message = "Password can not be blank !")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$", message = "Password must contains atleast one number, atleast one lowercase character, "
			+ "atleast one uppercase character, atleast one special case character and "
			+ "must be in between length of 8 to 20 characters.")
	private String password;
	
	@NotBlank(message = "About can not be blank !")
	private String about;
	
	private List<RoleDto> roles = new ArrayList<>();

}
