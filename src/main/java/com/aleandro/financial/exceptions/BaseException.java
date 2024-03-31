package com.aleandro.financial.exceptions;

import java.sql.Timestamp;

public abstract class BaseException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Timestamp timestamp ;
	protected String message;
	protected String details;

	public BaseException() {
		// TODO Auto-generated constructor stub
	}
	


	public Timestamp getTimestamp() {
		return timestamp;
	}



	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}



	public String getMessage() {
		return message;
	}



	public void setMessage(String message) {
		this.message = message;
	}



	public String getDetails() {
		return details;
	}



	public void setDetails(String details) {
		this.details = details;
	}
	

}
