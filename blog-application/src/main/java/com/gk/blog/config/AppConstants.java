package com.gk.blog.config;

public class AppConstants {

	public static final String PAGE_NUMBER = "0";
	public static final String PAGE_SIZE = "10";
	public static final String SORT_BY = "postId";
	public static final String SORT_DIRECTION = "asc";
	
	public static final Integer ROLE_ADMIN = 1;
	public static final Integer ROLE_USER = 2;
	
	public static final long JWT_TOKEN_VALIDITY_SECONDS = 5 * 60 * 60;
	public static final String SECRET_KEY = "jwtTokenKey";
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";

}
