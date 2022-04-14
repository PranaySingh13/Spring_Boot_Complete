package com.gk.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice // It handles exception globally overall Restcontroller APis.
public class GlobalExceptionHandler {

	/*
	 * The @ExceptionHandler is an annotation used to handle the specific exceptions
	 * and sending the custom responses to the client.
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleMethodArgsNotValidException(MethodArgumentNotValidException ex) {
		Map<String, String> errorMap = new HashMap<String, String>();
		ex.getBindingResult().getFieldErrors()
				.forEach(error -> errorMap.put(error.getField(), error.getDefaultMessage()));

		return errorMap;
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(UserNotFoundException.class)
	public Map<String, String> resourceNotFoundExceptionHandler(UserNotFoundException ex) {
		Map<String, String> errorMap = new HashMap<String, String>();
		errorMap.put("errorMessage", ex.getMessage());
		return errorMap;
	}
}
