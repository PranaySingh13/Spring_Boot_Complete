package com.gk.blog.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
		Post post = postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
		Post updatedPost = postRepo.save(post);
		return mapper.map(updatedPost, PostDto.class);
	}

	@Override
	public PostDto deletePost(int postId) throws ResourceNotFoundException {
		Post post = postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));
		postRepo.delete(post);
		return mapper.map(post, PostDto.class);
	}

	@Override
	public List<PostDto> getAllPosts(Integer pageNumber, Integer pageSize) {

		// Creates a new unsorted PageRequest.
		Pageable p = PageRequest.of(pageNumber, pageSize);

		/*
		 * Returns a Page of entities meeting the paging restriction provided in the
		 * Pageable object.
		 */
		Page<Post> pagePost = postRepo.findAll(p);

		// Returns the page content as List.
		List<Post> allPosts = pagePost.getContent();
		List<PostDto> postDtos = allPosts.stream().map(post -> mapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public PostDto getPostById(int postId) throws ResourceNotFoundException {
		Post post = postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));
		return mapper.map(post, PostDto.class);
	}

	@Override
	public List<PostDto> getPostsByCategory(int categoryId) throws ResourceNotFoundException {
		Category category = catRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "category id", categoryId));
		List<Post> posts = postRepo.findByCategory(category);
		List<PostDto> postDtos = posts.stream().map(post -> mapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public List<PostDto> getPostsByUser(int userId) throws ResourceNotFoundException {
		User user = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "user id", userId));
		List<Post> posts = postRepo.findByUser(user);
		List<PostDto> postDtos = posts.stream().map(post -> mapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		return postDtos;
	}

}
