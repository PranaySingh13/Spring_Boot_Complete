package com.gk.blog.payloads;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {

	private int id;

	@NotBlank(message = "Content can not be blank !")
	@Size(min = 10, max = 10000, message = "Content should be in between length of 10 to 10000 words ! ")
	private String content;

}
