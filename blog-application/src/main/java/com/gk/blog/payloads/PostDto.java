package com.gk.blog.payloads;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {

	private String title;

	private String content;

	private Date addedDate;

	private String imageName;

	/*
	 * We are Using DTO rather than entity here because both entity contains
	 * List<Post> so it will create infinite recursion calls for creating Post and
	 * both DTO class don't contains List<Post>.
	 */
	private CategoryDto category;

	private UserDto user;

}
