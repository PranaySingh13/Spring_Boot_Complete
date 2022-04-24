package com.gk.blog.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.gk.blog.entity.Category;
import com.gk.blog.entity.Post;
import com.gk.blog.entity.User;
import com.gk.blog.exceptions.ResourceNotFoundException;
import com.gk.blog.payloads.PostDto;
import com.gk.blog.payloads.PostResponse;
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
	public PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDirection) {

		// Creating Sort Object
		Sort sort = sortDirection.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

		// Creates a new unsorted PageRequest.
		Pageable p = PageRequest.of(pageNumber, pageSize, sort);

		/*
		 * Returns a Page of entities meeting the paging restriction provided in the
		 * Pageable object.
		 */
		Page<Post> pagePost = postRepo.findAll(p);

		// Returns the page content as List.
		List<Post> allPosts = pagePost.getContent();
		List<PostDto> postDtos = allPosts.stream().map(post -> mapper.map(post, PostDto.class))
				.collect(Collectors.toList());

		PostResponse postResponse = new PostResponse();
		postResponse.setContent(postDtos);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getNumberOfElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setFirstPage(pagePost.isFirst());
		postResponse.setLastPage(pagePost.isLast());
		return postResponse;
	}

	@Override
	public PostDto getPostById(int postId) throws ResourceNotFoundException {
		Post post = postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));
		return mapper.map(post, PostDto.class);
	}

	@Override
	public PostResponse getPostsByCategory(Integer pageNumber, Integer pageSize, String sortBy, String sortDirection,
			Integer categoryId) throws ResourceNotFoundException {

		Sort sort = sortDirection.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

		Pageable p = PageRequest.of(pageNumber, pageSize, sort);

		Category category = catRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "category id", categoryId));

		Page<Post> pagePost = postRepo.findByCategory(category, p);

		List<Post> allPostsByCategory = pagePost.getContent();
		List<PostDto> postDtos = allPostsByCategory.stream().map(post -> mapper.map(post, PostDto.class))
				.collect(Collectors.toList());

		PostResponse postResponse = new PostResponse();
		postResponse.setContent(postDtos);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getNumberOfElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setFirstPage(pagePost.isFirst());
		postResponse.setLastPage(pagePost.isLast());
		return postResponse;
	}

	@Override
	public PostResponse getPostsByUser(Integer pageNumber, Integer pageSize, String sortBy, String sortDirection,
			Integer userId) throws ResourceNotFoundException {

		Sort sort = sortDirection.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

		Pageable p = PageRequest.of(pageNumber, pageSize, sort);

		User user = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "user id", userId));

		Page<Post> pagePost = postRepo.findByUser(user, p);

		List<Post> allPostsByUser = pagePost.getContent();

		List<PostDto> postDtos = allPostsByUser.stream().map(post -> mapper.map(post, PostDto.class))
				.collect(Collectors.toList());

		PostResponse postResponse = new PostResponse();
		postResponse.setContent(postDtos);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getNumberOfElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setFirstPage(pagePost.isFirst());
		postResponse.setLastPage(pagePost.isLast());
		return postResponse;
	}

}
