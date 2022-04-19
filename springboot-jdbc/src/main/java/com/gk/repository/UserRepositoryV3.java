package com.gk.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.gk.entity.User;

@Repository
public class UserRepositoryV3 {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	private SimpleJdbcCall simpleJdbcCall;

	public void addUser(User user) {
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withSchemaName("springbootjdbcdb")
					.withProcedureName("insertUser");

			/* Two Ways to map named parameters */
			// 1. Parameter values through Map
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("uid", user.getId());
			map.put("uname", user.getName());
			map.put("uage", user.getAge());
			map.put("ucity", user.getCity());

			/*
			 * 2.Parameter Values through SqlParameterSource interface implemented class
			 * MapSqlParameterSource(Only work with Named Parameters not with Positional
			 * Parameters)
			 */
			/*
			 * SqlParameterSource params = new MapSqlParameterSource()
			 * .addValue("uid",user.getId()) 
			 * .addValue("uname", user.getName())
			 * .addValue("uage",user.getAge()) 
			 * .addValue("ucity", user.getCity());
			 * 
			 * simpleJdbcCall.execute(params);
			 */
			simpleJdbcCall.execute(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public User getUser(int userId) {
		User user = null;
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withSchemaName("springbootjdbcdb")
					.withProcedureName("getUser");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("no", userId);
			Map<String, Object> responseMap = simpleJdbcCall.execute(map);

			// Getting User from Map Object
			int id = (int) responseMap.get("uid");
			String name = (String) responseMap.get("uname");
			int age = (int) responseMap.get("uage");
			String city = (String) responseMap.get("ucity");
			user = new User();
			user.setId(id);
			user.setName(name);
			user.setAge(age);
			user.setCity(city);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	public List<User> getAllUsers() {
		List<User> users = null;
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withSchemaName("springbootjdbcdb")
					.withProcedureName("getAllUsers");
			Map<String, Object> responseMap = simpleJdbcCall.execute();

			for (User user : users) {
				// Getting User from Map Object
				int id = (int) responseMap.get("uid");
				String name = (String) responseMap.get("uname");
				int age = (int) responseMap.get("uage");
				String city = (String) responseMap.get("ucity");
				user = new User();
				user.setId(id);
				user.setName(name);
				user.setAge(age);
				user.setCity(city);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}

	public void updateUser(User user, int userId) {
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withSchemaName("springbootjdbcdb")
					.withProcedureName("updateUser");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("uid", userId);
			map.put("uname", user.getName());
			map.put("uage", user.getAge());
			map.put("ucity", user.getCity());
			simpleJdbcCall.execute(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteUser(int userId) {
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withSchemaName("springbootjdbcdb")
					.withProcedureName("deleteUser");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("uid", userId);
			simpleJdbcCall.execute(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
