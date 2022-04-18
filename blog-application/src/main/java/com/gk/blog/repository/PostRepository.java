package com.gk.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gk.blog.entity.Category;
import com.gk.blog.entity.Post;
import com.gk.blog.entity.User;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

	/*
	 * This is functioning of Spring Data JPA first just add findBy and second
	 * append entity field in java camelCase notation in method name so that JPA
	 * understands this method irrespective of applying @Query in repository
	 * interface method created to select user based on this field.
	 * 
	 * Ex:- findByUserId, findByEmail etc.
	 */
	List<Post> findByUser(User user);

	List<Post> findByCategory(Category category);

}
