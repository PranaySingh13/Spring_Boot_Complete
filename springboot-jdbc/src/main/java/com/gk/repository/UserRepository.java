package com.gk.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.gk.entity.User;
import com.gk.mapper.UserMapper;

@Repository
public class UserRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private UserMapper userMapper;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Bean
	public void createTable() {
		String query = "CREATE TABLE IF NOT EXISTS user(id int(11) primary key, name varchar(45), age int(11), city varchar(100))";
		jdbcTemplate.update(query);
	}

	private static final String INSERT_QUERY = "insert into user (id,name,age,city) values (?,?,?,?)";
	private static final String UPDATE_QUERY = "update user SET name = ?, age = ?, city = ? where id = ?";
	private static final String SELECT_QUERY = "select * from user where id = ?";
	private static final String SELECT_ALL_QUERY = "select * from user";
	private static final String DELETE_QUERY = "delete from user where id = ?";

	public void addUser(User user) {
		jdbcTemplate.update(INSERT_QUERY, user.getId(), user.getName(), user.getAge(), user.getCity());
	}

	// selecting single user
	public User getUser(int userId) {
		/*
		 * <T> T queryForObject(String sql, RowMapper<T> rowMapper, Object... args)
		 * 
		 * Query given SQL to create a prepared statement from SQL and a list of
		 * arguments to bind to the query, mapping a single result row to a result
		 * object via a RowMapper.
		 */
		User user = jdbcTemplate.queryForObject(SELECT_QUERY, userMapper, userId);
		return user;
	}

	// selecting list of users
	public List<User> getAllUsers() {
		/*
		 * <T> List<T> query(String sql, RowMapper<T> rowMapper)
		 * 
		 * Execute a query given static SQL, mapping each row to a result object via a
		 * RowMapper.
		 */
		List<User> users = jdbcTemplate.query(SELECT_ALL_QUERY, userMapper);
		return users;
	}

	public void updateUser(User user, int userId) {
		jdbcTemplate.update(UPDATE_QUERY, user.getName(), user.getAge(), user.getCity(), userId);
	}

	public void deleteUser(int userId) {
		jdbcTemplate.update(DELETE_QUERY, userId);
	}

}
