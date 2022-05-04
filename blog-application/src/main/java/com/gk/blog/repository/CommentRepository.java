package com.gk.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gk.blog.entity.Comment;
import com.gk.blog.entity.Post;
import com.gk.blog.entity.User;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

	List<Comment> findByUser(User user);

	List<Comment> findByPost(Post post);

}
