package com.example.creditCard.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BlackListCardNotFoundException extends RuntimeException{
	
	public BlackListCardNotFoundException(String message) {
		super(message);
	}

}
