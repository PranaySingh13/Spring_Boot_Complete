package com.gk.blog.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.gk.blog.payloads.ApiResponse;

@RestControllerAdvice // It handles exception globally overall Restcontroller APis.
public class GlobalExceptionHandler {

	/*
	 * The @ExceptionHandler is an annotation used to handle the specific exceptions
	 * and sending the custom responses to the client.
	 */
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex) {
		String message = ex.getMessage();
		ApiResponse apiResponse = new ApiResponse(message, false);
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
	}

}
