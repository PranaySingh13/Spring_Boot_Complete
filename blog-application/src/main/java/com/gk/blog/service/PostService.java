package com.gk.blog.service;

import com.gk.blog.exceptions.ResourceNotFoundException;
import com.gk.blog.payloads.PostDto;
import com.gk.blog.payloads.PostResponse;

public interface PostService {

	PostDto createPost(PostDto postDto, int userId, int categoryId) throws ResourceNotFoundException;

	PostDto updatePost(PostDto postDto, int postId) throws ResourceNotFoundException;

	PostDto deletePost(int postId) throws ResourceNotFoundException;

	PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDirection);

	PostDto getPostById(int postId) throws ResourceNotFoundException;

	PostResponse getPostsByCategory(Integer pageNumber, Integer pageSize, Integer categoryId)
			throws ResourceNotFoundException;

	PostResponse getPostsByUser(Integer pageNumber, Integer pageSize, Integer userId) throws ResourceNotFoundException;

}
