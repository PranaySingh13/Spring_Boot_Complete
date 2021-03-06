package com.gk.blog.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gk.blog.config.AppConstants;
import com.gk.blog.exceptions.ResourceNotFoundException;
import com.gk.blog.payloads.ApiResponse;
import com.gk.blog.payloads.PostDto;
import com.gk.blog.payloads.PostResponse;
import com.gk.blog.service.FileService;
import com.gk.blog.service.PostService;

@RestController
@RequestMapping("api/post")
public class PostController {

	public static final Logger logger = LoggerFactory.getLogger(PostController.class);

	@Autowired
	private PostService postService;

	@Autowired
	private FileService fileService;

	@Value("${project.files}")
	private String path;

	@PreAuthorize("hasAnyAuthority('ADMIN','USER')")
	@PostMapping("/user/{userId}/category/{categoryId}")
	public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto, @PathVariable int userId,
			@PathVariable int categoryId) throws ResourceNotFoundException {
		PostDto createdPost = postService.createPost(postDto, userId, categoryId);
		return new ResponseEntity<PostDto>(createdPost, HttpStatus.CREATED);
	}

	// Authorization Handled in SecurityConfig.class for get mapping
	@GetMapping("/category/{categoryId}")
	public ResponseEntity<PostResponse> getPostsByCategory(
			@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDirection", defaultValue = AppConstants.SORT_DIRECTION, required = false) String sortDirection,
			@PathVariable int categoryId) throws ResourceNotFoundException {

		logger.debug("Request{} Page Number: " + pageNumber + ", Page Size: " + pageSize + ", Sort By: " + sortBy
				+ ", Sort Direction: " + sortDirection);

		PostResponse postResponse = postService.getPostsByCategory(pageNumber, pageSize, sortBy, sortDirection,
				categoryId);

		logger.debug("Returning Post Response By Category");

		return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);
	}

	// Authorization Handled in SecurityConfig.class for get mapping
	@GetMapping("/user/{userId}")
	public ResponseEntity<PostResponse> getPostsByUser(
			@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDirection", defaultValue = AppConstants.SORT_DIRECTION, required = false) String sortDirection,
			@PathVariable int userId) throws ResourceNotFoundException {

		logger.debug("Request{} Page Number: " + pageNumber + ", Page Size: " + pageSize + ", Sort By: " + sortBy
				+ ", Sort Direction: " + sortDirection);

		PostResponse postResponse = postService.getPostsByUser(pageNumber, pageSize, sortBy, sortDirection, userId);

		logger.debug("Returning Post Response By User");

		return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);
	}

	// Authorization Handled in SecurityConfig.class for get mapping
	@GetMapping("/{postId}")
	public ResponseEntity<PostDto> getPostsById(@PathVariable int postId) throws ResourceNotFoundException {
		PostDto postById = postService.getPostById(postId);
		return new ResponseEntity<PostDto>(postById, HttpStatus.OK);
	}

	// Authorization Handled in SecurityConfig.class for get mapping
	@GetMapping("/all")
	public ResponseEntity<PostResponse> getAllPosts(
			@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDirection", defaultValue = AppConstants.SORT_DIRECTION, required = false) String sortDirection) {

		logger.debug("Request{} Page Number: " + pageNumber + ", Page Size: " + pageSize + ", Sort By: " + sortBy
				+ ", Sort Direction: " + sortDirection);

		PostResponse postResponse = postService.getAllPosts(pageNumber, pageSize, sortBy, sortDirection);

		logger.debug("Returning Post Response");

		return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);
	}

	@PreAuthorize("hasAnyAuthority('ADMIN','USER')")
	@PutMapping("/{postId}")
	public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto, @PathVariable int postId)
			throws ResourceNotFoundException {
		PostDto updatedPost = postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatedPost, HttpStatus.CREATED);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@DeleteMapping("/{postId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable int postId) throws ResourceNotFoundException {
		PostDto deletePost = postService.deletePost(postId);
		return new ResponseEntity<ApiResponse>(
				new ApiResponse("Post with " + deletePost.getTitle() + " title deleted successfully", true),
				HttpStatus.OK);
	}

	// Authorization Handled in SecurityConfig.class for get mapping
	@GetMapping("/posts/search/{keyword}")
	public ResponseEntity<List<PostDto>> searchPostByTitleKeyword(@PathVariable String keyword) {
		List<PostDto> searchedPosts = postService.searchPostByTitleKeyword(keyword);
		return new ResponseEntity<List<PostDto>>(searchedPosts, HttpStatus.OK);
	}

	// post image upload
	@PreAuthorize("hasAnyAuthority('ADMIN','USER')")
	@PostMapping("/file/upload/{postId}")
	public ResponseEntity<PostDto> uploadPostFile(@Valid @RequestParam("file") MultipartFile file,
			@PathVariable int postId) throws IOException, ResourceNotFoundException {
		PostDto postDto = postService.getPostById(postId);
		String fileName = fileService.fileUpload(path, file);
		postDto.setImageName(fileName);
		PostDto updatePostDto = postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatePostDto, HttpStatus.OK);
	}

	// post image download
	// Authorization Handled in SecurityConfig.class for get mapping
	@GetMapping(value = "/file/download/{fileName}", produces = MediaType.ALL_VALUE)
	public void fileDownload(@PathVariable String fileName, HttpServletResponse response) throws IOException {
		InputStream resource = fileService.fileDownload(path, fileName);
		response.setContentType(MediaType.ALL_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());
	}

}
