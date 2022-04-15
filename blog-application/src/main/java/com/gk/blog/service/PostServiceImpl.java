package com.gk.blog.service;

import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gk.blog.entity.Category;
import com.gk.blog.entity.Post;
import com.gk.blog.entity.User;
import com.gk.blog.exceptions.ResourceNotFoundException;
import com.gk.blog.payloads.PostDto;
import com.gk.blog.repository.CategoryRepository;
import com.gk.blog.repository.PostRepository;
import com.gk.blog.repository.UserRepository;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository postRepo;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private CategoryRepository catRepo;

	@Autowired
	private ModelMapper mapper;

	@Override
	public PostDto createPost(PostDto postDto, int userId, int categoryId) throws ResourceNotFoundException {

		User user = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "user id", userId));
		Category category = catRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "category id", categoryId));
		Post post = mapper.map(postDto, Post.class);
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		Post createdPost = postRepo.save(post);
		return mapper.map(createdPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, int postId) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PostDto deletePost(int postId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PostDto> getAllPosts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PostDto getPostById(int postId) {
		// TODO Auto-generated method stub
		return null;
	}

}
