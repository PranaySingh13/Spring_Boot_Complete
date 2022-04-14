package com.gk.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
	
	private int userId;
	
	@NotBlank(message = "Name cannot be blank!")
	@Size(min = 4,max = 26,message = "Name must lie in between 4 and 26 characters! ")
	private String name;

	@NotBlank(message = "Email cannot be blank! ")
	@Email(message = "Email is not Valid !")
	private String email;
	
	@NotBlank(message = "Mobile cannot be blank")
	@Pattern(regexp = "^\\d{10}$",message = "Mobile number should have ten digits")
	private String mobile;
	
	@NotBlank(message = "Gender cannot be blank")
	private String gender;
	
	@Min(value=18, message="Age must be equal or greater than 18")  
    @Max(value=45, message="Age must be equal or less than 45")  
	private int age;
	
	@NotBlank(message = "Nationality cannot be blank")
	private String nationality;

}
