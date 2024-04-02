package com.aleandro.financial.exceptions;

import java.sql.Timestamp;

public class InvalidValueException extends BaseException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public InvalidValueException() {
		// TODO Auto-generated constructor stub
	}
	public InvalidValueException(String message) {
		this.message = message;
		this.timestamp = new Timestamp(System.currentTimeMillis());
	}

}
