package com.gk.blog.controllers;

import java.util.List;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RestController;

import com.gk.blog.exceptions.ResourceNotFoundException;
import com.gk.blog.payloads.PostDto;
import com.gk.blog.service.PostService;

@RestController
@RequestMapping("api/post")
public class PostController {

	@Autowired
	private PostService postService;

	@PostMapping("/user/{userId}/category/{categoryId}")
	public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto, @PathVariable int userId,
			@PathVariable int categoryId) throws ResourceNotFoundException {
		PostDto createdPost = postService.createPost(postDto, userId, categoryId);
		return new ResponseEntity<PostDto>(createdPost, HttpStatus.CREATED);
	}

	@GetMapping("/category/{categoryId}")
	public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable int categoryId)
			throws ResourceNotFoundException {
		List<PostDto> postsByCategory = postService.getPostsByCategory(categoryId);
		return new ResponseEntity<List<PostDto>>(postsByCategory, HttpStatus.OK);
	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable int userId) throws ResourceNotFoundException {
		List<PostDto> postsByUser = postService.getPostsByUser(userId);
		return new ResponseEntity<List<PostDto>>(postsByUser, HttpStatus.OK);
	}

	@GetMapping("/{postId}")
	public ResponseEntity<PostDto> getPostsById(@PathVariable int postId) throws ResourceNotFoundException {
		PostDto postById = postService.getPostById(postId);
		return new ResponseEntity<PostDto>(postById, HttpStatus.OK);
	}

	@GetMapping("/all")
	public ResponseEntity<List<PostDto>> getAllPosts() {
		List<PostDto> posts = postService.getAllPosts();
		return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
	}

	@PutMapping("/{postId}")
	public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto, @PathVariable int postId)
			throws ResourceNotFoundException {
		PostDto updatedPost = postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatedPost, HttpStatus.CREATED);
	}

	@DeleteMapping("/{postId}")
	public ResponseEntity<PostDto> deletePost(@PathVariable int postId) throws ResourceNotFoundException {
		PostDto deletePost = postService.deletePost(postId);
		return new ResponseEntity<PostDto>(deletePost, HttpStatus.OK);
	}

}
