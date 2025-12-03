package com.jspiders.SpringBoot_DemoProject.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {
	@ExceptionHandler(value = IllegalArgumentException.class)
	public ResponseEntity<?> illegalArgumentException(IllegalArgumentException exception) {
		ApiError apiError = new ApiError();
		apiError.setMessage(exception.getMessage());
		apiError.setClassName(exception.getClass());
		apiError.setTimeStamp(LocalDateTime.now());
		return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(value = EmailNotFoundException.class)
	public ResponseEntity<?> emailNotFoundException(EmailNotFoundException exception) {
		ApiError apiError = new ApiError();
		apiError.setMessage(exception.getMessage());
		apiError.setClassName(exception.getClass());
		apiError.setTimeStamp(LocalDateTime.now());
		return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(value = InvalidUserId.class)
	public ResponseEntity<?> invalidUserId(InvalidUserId exception) {
		ApiError apiError = new ApiError();
		apiError.setMessage(exception.getMessage());
		apiError.setClassName(exception.getClass());
		apiError.setTimeStamp(LocalDateTime.now());
		return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
