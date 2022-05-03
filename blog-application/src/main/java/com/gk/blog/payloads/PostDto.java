package com.gk.blog.payloads;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.gk.blog.entity.Comment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {

	@NotBlank(message = "Title can not be blank !")
	@Size(min = 10, max = 100, message = "Title should be in between length of 10 to 100 words ! ")
	private String title;

	@NotBlank(message = "Content can not be blank !")
	@Size(min = 10, max = 10000, message = "Content should be in between length of 10 to 10000 words ! ")
	private String content;

	private Date addedDate;

	@NotBlank(message = "Image Name can not be blank !")
	private String imageName;

	/*
	 * We are Using DTO rather than entity here because both entity contains
	 * List<Post> so it will create infinite recursion calls for creating Post and
	 * both DTO class don't contains List<Post>.
	 */
	private CategoryDto category;

	private UserDto user;
	
	private List<Comment> comments=new ArrayList<>();

}
