package com.example.creditCard.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TransactionsNotFoundException extends RuntimeException{
	
	public TransactionsNotFoundException(String message) {
		super(message);
	}

}
