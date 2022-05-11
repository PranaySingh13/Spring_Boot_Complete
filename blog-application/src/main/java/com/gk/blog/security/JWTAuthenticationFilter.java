package com.gk.blog.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;

//It will validate the token and check the user details whether they are correct or not before every api call
@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JWTTokenHelper jwtTokenHelper;

	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		// 1. Get Token
		String requestToken = request.getHeader(HEADER_STRING);
		String usernameFromToken = null;
		String token = null;

		// 2. check request token is not null and starts with token prefix
		if (requestToken != null && requestToken.startsWith(TOKEN_PREFIX)) {

			// 3. get token without prefix
			token = requestToken.replace(TOKEN_PREFIX, "");

			// 4. get username from token with the helper
			try {
				usernameFromToken = jwtTokenHelper.getUsernameFromToken(token);
			} catch (IllegalArgumentException e) {
				logger.error("An Error occured during getting username from token", e);
			} catch (ExpiredJwtException e) {
				logger.error("The token is expired and not valid anymore", e);
			} catch (MalformedJwtException e) {
				logger.error("Invalid JWT", e);
			} catch (SignatureException e) {
				logger.error("Authentication Failed, username or password not valid", e);
			}
		} else {
			logger.warn("JWT Token does not begin with Bearer");
		}

		/*
		 * 5. once we get username ,now validate the token via checking that
		 * usernamefromtoken is not null and Security Context Holder should be null
		 * because it associates a given SecurityContext with the current execution
		 * thread
		 */
		if (usernameFromToken != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			// 6. get the user details from username
			UserDetails userDetails = userDetailsService.loadUserByUsername(usernameFromToken);

			// 7. validate token with the helper it will either return true or false
			if (jwtTokenHelper.validateToken(token, userDetails)) {

				// 8. get authentication
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				// 9. Set details in authentication
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				logger.info("Authenticated User " + usernameFromToken + ", setting security context");

				// 10. after validating, setting authentication in security context
				SecurityContextHolder.getContext().setAuthentication(authentication);
			} else {
				logger.error("Invalid JWT Token");
			}
		} else {
			logger.error("Username From Token is null or Security Context is not null");
		}

		// 11. After validating token, filter the request before request moves forward
		filterChain.doFilter(request, response);

	}

}
