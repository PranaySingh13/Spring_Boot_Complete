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
import com.gk.blog.payloads.ApiResponse;
import com.gk.blog.payloads.CommentDto;
import com.gk.blog.service.CommentService;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

	@Autowired
	private CommentService commentService;

	@PostMapping("/user/{userId}/post/{postId}")
	public ResponseEntity<CommentDto> createComment(@Valid @RequestBody CommentDto commentDto,
			@PathVariable Integer userId, @PathVariable Integer postId) throws ResourceNotFoundException {
		CommentDto createdComment = commentService.createComment(commentDto, userId, postId);
		return new ResponseEntity<CommentDto>(createdComment, HttpStatus.CREATED);
	}

	@DeleteMapping("/{commentId}")
	public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId) throws ResourceNotFoundException {
		CommentDto deletedComment = commentService.deleteComment(commentId);
		return new ResponseEntity<ApiResponse>(
				new ApiResponse("Comment " + deletedComment.getContent() + " deleted successfully", true),
				HttpStatus.OK);
	}

	@PutMapping("/{commentId}")
	public ResponseEntity<CommentDto> updateComment(@Valid @RequestBody CommentDto commentDto,
			@PathVariable Integer commentId) throws ResourceNotFoundException {
		CommentDto updatedComment = commentService.updateComment(commentDto, commentId);
		return new ResponseEntity<CommentDto>(updatedComment, HttpStatus.CREATED);
	}

	@GetMapping("/all")
	public ResponseEntity<List<CommentDto>> getAllComments() {
		List<CommentDto> allComments = commentService.getAllComments();
		return new ResponseEntity<List<CommentDto>>(allComments, HttpStatus.OK);
	}

	@GetMapping("/all/post/{postId}")
	public ResponseEntity<List<CommentDto>> getCommentsByPost(@PathVariable int postId)
			throws ResourceNotFoundException {
		List<CommentDto> allCommentsByPost = commentService.getCommentsByPost(postId);
		return new ResponseEntity<List<CommentDto>>(allCommentsByPost, HttpStatus.OK);
	}

	@GetMapping("/all/user/{userId}")
	public ResponseEntity<List<CommentDto>> getCommentsByUser(@PathVariable int userId)
			throws ResourceNotFoundException {
		List<CommentDto> allCommentsByUser = commentService.getCommentsByUser(userId);
		return new ResponseEntity<List<CommentDto>>(allCommentsByUser, HttpStatus.OK);
	}

}
