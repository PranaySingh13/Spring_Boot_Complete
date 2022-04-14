package com.gk.blog.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "post")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int postId;

	@Column(name = "post_title",length = 100,nullable = false)
	private String title;

	@Column(name = "post_content",length = 10000,nullable = false)
	private String content;

	@Column
	private Date addedDate;

	@Column
	private String imageName;
	
	//Bidirectional Mapping
	@ManyToOne
	@JoinColumn(name="category_id")
	private Category category;
	
	//Bidirectional Mapping
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
}
