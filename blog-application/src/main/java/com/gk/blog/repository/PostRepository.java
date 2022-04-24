package com.gk.blog.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

	// custom pagination
	Page<Post> findByUser(User user, Pageable p);

	// custom pagination
	Page<Post> findByCategory(Category category, Pageable p);

	/*
	 * We include our wild cards in the query we supply. The @Param annotation is
	 * important here because we're using a named parameter.
	 */
	@Query("SELECT p FROM Post p WHERE p.title LIKE %:keywords%")
	List<Post> searchPostByTitleKeyword(@Param("keywords") String keywords);

}
