package com.store.book.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmptyBasketException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EmptyBasketException(String message) {
		super(message);
	}

}
