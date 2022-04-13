package com.gk.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Users_1")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(staticName = "build")//to map with the UserDto coming as payload. Similar function as ModelMapper. 
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;

	@Column
	private String name;

	@Column
	private String email;

	@Column
	private String mobile;

	@Column
	private String gender;

	@Column
	private int age;

	@Column
	private String nationality;

}
