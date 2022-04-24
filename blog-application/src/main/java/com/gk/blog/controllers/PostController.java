package com.gk.blog.controllers;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gk.blog.exceptions.ResourceNotFoundException;
import com.gk.blog.payloads.ApiResponse;
import com.gk.blog.payloads.PostDto;
import com.gk.blog.payloads.PostResponse;
import com.gk.blog.service.PostService;

@RestController
@RequestMapping("api/post")
public class PostController {

	Logger logger = LoggerFactory.getLogger(PostController.class);

	@Autowired
	private PostService postService;

	@PostMapping("/user/{userId}/category/{categoryId}")
	public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto, @PathVariable int userId,
			@PathVariable int categoryId) throws ResourceNotFoundException {
		PostDto createdPost = postService.createPost(postDto, userId, categoryId);
		return new ResponseEntity<PostDto>(createdPost, HttpStatus.CREATED);
	}

	@GetMapping("/category/{categoryId}")
	public ResponseEntity<PostResponse> getPostsByCategory(
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = "postId", required = false) String sortBy,
			@RequestParam(value = "sortDirection", defaultValue = "asc", required = false) String sortDirection,
			@PathVariable int categoryId) throws ResourceNotFoundException {

		logger.debug("Request{} Page Number: " + pageNumber + ", Page Size: " + pageSize + ", Sort By: " + sortBy
				+ ", Sort Direction: " + sortDirection);

		PostResponse postResponse = postService.getPostsByCategory(pageNumber, pageSize, sortBy, sortDirection,
				categoryId);

		logger.debug("Returning Post Response By Category");

		return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);
	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<PostResponse> getPostsByUser(
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = "postId", required = false) String sortBy,
			@RequestParam(value = "sortDirection", defaultValue = "asc", required = false) String sortDirection,
			@PathVariable int userId) throws ResourceNotFoundException {

		logger.debug("Request{} Page Number: " + pageNumber + ", Page Size: " + pageSize + ", Sort By: " + sortBy
				+ ", Sort Direction: " + sortDirection);

		PostResponse postResponse = postService.getPostsByUser(pageNumber, pageSize, sortBy, sortDirection, userId);

		logger.debug("Returning Post Response By User");

		return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);
	}

	@GetMapping("/{postId}")
	public ResponseEntity<PostDto> getPostsById(@PathVariable int postId) throws ResourceNotFoundException {
		PostDto postById = postService.getPostById(postId);
		return new ResponseEntity<PostDto>(postById, HttpStatus.OK);
	}

	@GetMapping("/all")
	public ResponseEntity<PostResponse> getAllPosts(
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = "postId", required = false) String sortBy,
			@RequestParam(value = "sortDirection", defaultValue = "asc", required = false) String sortDirection) {

		logger.debug("Request{} Page Number: " + pageNumber + ", Page Size: " + pageSize + ", Sort By: " + sortBy
				+ ", Sort Direction: " + sortDirection);

		PostResponse postResponse = postService.getAllPosts(pageNumber, pageSize, sortBy, sortDirection);

		logger.debug("Returning Post Response");

		return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);
	}

	@PutMapping("/{postId}")
	public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto, @PathVariable int postId)
			throws ResourceNotFoundException {
		PostDto updatedPost = postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatedPost, HttpStatus.CREATED);
	}

	@DeleteMapping("/{postId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable int postId) throws ResourceNotFoundException {
		PostDto deletePost = postService.deletePost(postId);
		return new ResponseEntity<ApiResponse>(
				new ApiResponse("Post with " + deletePost.getTitle() + " title deleted successfully", true),
				HttpStatus.OK);
	}

}
