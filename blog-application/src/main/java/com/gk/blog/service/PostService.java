package com.gk.blog.service;

import java.util.List;

import com.gk.blog.exceptions.ResourceNotFoundException;
import com.gk.blog.payloads.PostDto;

public interface PostService {

	PostDto createPost(PostDto postDto, int userId, int categoryId) throws ResourceNotFoundException;

	PostDto updatePost(PostDto postDto, int postId) throws ResourceNotFoundException;

	PostDto deletePost(int postId) throws ResourceNotFoundException;

	List<PostDto> getAllPosts(Integer pageNumber,Integer pageSize);

	PostDto getPostById(int postId) throws ResourceNotFoundException;

	List<PostDto> getPostsByCategory(int categoryId) throws ResourceNotFoundException;

	List<PostDto> getPostsByUser(int userId) throws ResourceNotFoundException;

}
