package com.gk.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.gk.entity.User;
import com.gk.mapper.UserMapper;

@Repository
public class UserRepositoryV2 extends JdbcDaoSupport {

	@Autowired
	private UserMapper userMapper;

	private static final String INSERT_QUERY = "insert into user (id,name,age,city) values (?,?,?,?)";
	private static final String UPDATE_QUERY = "update user SET name = ?, age = ?, city = ? where id = ?";
	private static final String SELECT_QUERY = "select * from user where id = ?";
	private static final String SELECT_ALL_QUERY = "select * from user";
	private static final String DELETE_QUERY = "delete from user where id = ?";

	public void addUser(User user) {
		getJdbcTemplate().update(INSERT_QUERY, user.getId(), user.getName(), user.getAge(), user.getCity());
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
		User user = getJdbcTemplate().queryForObject(SELECT_QUERY, userMapper, userId);
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
		List<User> users = getJdbcTemplate().query(SELECT_ALL_QUERY, userMapper);
		return users;
	}

	public void updateUser(User user, int userId) {
		getJdbcTemplate().update(UPDATE_QUERY, user.getName(), user.getAge(), user.getCity(), userId);
	}

	public void deleteUser(int userId) {
		getJdbcTemplate().update(DELETE_QUERY, userId);
	}

}
