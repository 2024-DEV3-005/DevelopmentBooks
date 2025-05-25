package com.store.book.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.store.book.response.model.ExceptionResponse;

import io.swagger.v3.oas.annotations.Hidden;

@Hidden
@RestControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(BookNotFoundException.class)
	public final ResponseEntity<ExceptionResponse> handleNotFoundException(Exception exception) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(exception.getMessage());
		logger.error(exception.getMessage());
		return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler({ EmptyBasketException.class, DuplicateBookEntryException.class })
	public final ResponseEntity<ExceptionResponse> handleBadRequestException(Exception exception) {

		ExceptionResponse exceptionResponse = new ExceptionResponse(exception.getMessage());
		logger.error(exception.getMessage());
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}

}
