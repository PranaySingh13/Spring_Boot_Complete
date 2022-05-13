package com.gk.blog.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "role")
@Getter
@Setter
public class Role {

	/*
	 * Not using auto or identity as generated type because we are handling
	 * explicitly in BlogApplication.class
	 */
	@Id
	private int id;

	@Column
	private String name;

}
