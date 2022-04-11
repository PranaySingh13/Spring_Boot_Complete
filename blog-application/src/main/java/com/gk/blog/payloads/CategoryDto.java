package com.gk.blog.payloads;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {

	private int categoryId;

	@NotBlank(message = "Title can not be blank !")
	private String categoryTitle;

	@NotBlank(message = "Description can not be blank !")
	@Size(min = 10, max = 100, message = "Description should be in between length of 10 to 100 words ! ")
	private String categoryDescription;

}
