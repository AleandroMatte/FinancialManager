package com.aleandro.financial.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class UnsupportedPathVariable extends RuntimeException {



	private static final long serialVersionUID = 1L;

	public UnsupportedPathVariable(String message) {
		super(message);
	}
	
	
	

}
