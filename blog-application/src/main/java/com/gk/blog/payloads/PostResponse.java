package com.gk.blog.payloads;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostResponse {

	private List<PostDto> content;
	private int pageNumber;
	private int pageSize;
	private int totalElements;
	private int totalPages;
	private boolean lastPage;
	private boolean firstPage;

}
