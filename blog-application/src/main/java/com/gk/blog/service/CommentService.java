package com.gk.blog.service;

import java.util.List;

import com.gk.blog.exceptions.ResourceNotFoundException;
import com.gk.blog.payloads.CommentDto;

public interface CommentService {

	CommentDto createComment(CommentDto commentDto, Integer userId, Integer postId) throws ResourceNotFoundException;

	CommentDto updateComment(CommentDto commentDto, Integer commentId) throws ResourceNotFoundException;

	CommentDto deleteComment(Integer commentId) throws ResourceNotFoundException;

	List<CommentDto> getAllComments();

	List<CommentDto> getCommentsByPost(Integer postId) throws ResourceNotFoundException;

	List<CommentDto> getCommentsByUser(Integer userId) throws ResourceNotFoundException;

}
