package com.aleandro.financial.exceptions;

import java.sql.Timestamp;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class DataNotFoundException extends BaseException {
	
	private static final long serialVersionUID = 1L;

	public DataNotFoundException() {
		// TODO Auto-generated constructor stub
	}
	public DataNotFoundException(String message) {
		this.message = message;
		this.timestamp = new Timestamp(System.currentTimeMillis());
	}

}
