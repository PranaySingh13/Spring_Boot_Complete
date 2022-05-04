package com.gk.blog.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gk.blog.entity.Comment;
import com.gk.blog.entity.Post;
import com.gk.blog.entity.User;
import com.gk.blog.exceptions.ResourceNotFoundException;
import com.gk.blog.payloads.CommentDto;
import com.gk.blog.repository.CommentRepository;
import com.gk.blog.repository.PostRepository;
import com.gk.blog.repository.UserRepository;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private PostRepository postRepo;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private CommentRepository commentRepo;

	@Autowired
	private ModelMapper mapper;

	@Override
	public CommentDto createComment(CommentDto commentDto, Integer userId, Integer postId)
			throws ResourceNotFoundException {
		User user = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "user id", userId));
		Post post = postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));
		Comment comment = mapper.map(commentDto, Comment.class);
		comment.setPost(post);
		comment.setUser(user);
		Comment savedComment = commentRepo.save(comment);
		return mapper.map(savedComment, CommentDto.class);
	}

	@Override
	public CommentDto deleteComment(Integer commentId) throws ResourceNotFoundException {
		Comment comment = commentRepo.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "comment id", commentId));
		commentRepo.delete(comment);
		return mapper.map(comment, CommentDto.class);
	}

	@Override
	public CommentDto updateComment(CommentDto commentDto, Integer commentId) throws ResourceNotFoundException {
		Comment comment = commentRepo.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "comment id", commentId));
		comment.setContent(commentDto.getContent());
		Comment updatedComment = commentRepo.save(comment);
		return mapper.map(updatedComment, CommentDto.class);
	}

	@Override
	public List<CommentDto> getAllComments() {
		List<Comment> allComments = commentRepo.findAll();
		List<CommentDto> commentDtos = allComments.stream().map(comment -> mapper.map(comment, CommentDto.class))
				.collect(Collectors.toList());
		return commentDtos;
	}

	@Override
	public List<CommentDto> getCommentsByPost(Integer postId) throws ResourceNotFoundException {
		Post post = postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));
		List<Comment> postComments = commentRepo.findByPost(post);
		List<CommentDto> postCommentDtos = postComments.stream().map(comment -> mapper.map(comment, CommentDto.class))
				.collect(Collectors.toList());
		return postCommentDtos;
	}

	@Override
	public List<CommentDto> getCommentsByUser(Integer userId) throws ResourceNotFoundException {
		User user = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "user id", userId));
		List<Comment> userComments = commentRepo.findByUser(user);
		List<CommentDto> userCommentDtos = userComments.stream().map(comment -> mapper.map(comment, CommentDto.class))
				.collect(Collectors.toList());
		return userCommentDtos;
	}

}
