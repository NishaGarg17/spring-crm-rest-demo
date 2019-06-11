package com.luv2code.springdemo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomerRestExceptionHandler {
	// Add an exception handler for CustomerNotFoundException
	@ExceptionHandler
	public ResponseEntity<CustomerErrorResponse> handleException(CustomerNotFoundException exc) {
		// create customerErrorResponse
		CustomerErrorResponse error = new CustomerErrorResponse(HttpStatus.NOT_FOUND.value(), exc.getMessage(), System.currentTimeMillis());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
		
	}
	// Add an another exception handler to handle any exception (catch All)
	@ExceptionHandler
	public ResponseEntity<CustomerErrorResponse> handleException(Exception exc) {
		CustomerErrorResponse error = new CustomerErrorResponse(HttpStatus.BAD_REQUEST.value(), exc.getMessage(), System.currentTimeMillis());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
}
