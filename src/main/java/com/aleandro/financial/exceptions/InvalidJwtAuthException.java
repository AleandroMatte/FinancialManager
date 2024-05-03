package com.aleandro.financial.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(code = HttpStatus.FORBIDDEN )
public class InvalidJwtAuthException extends AuthenticationException {

	private static final long serialVersionUID = 1L;

	
	public InvalidJwtAuthException(String ex) {
		super(ex);
		// TODO Auto-generated constructor stub
	}

}
