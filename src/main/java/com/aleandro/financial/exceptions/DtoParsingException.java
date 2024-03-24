package com.aleandro.financial.exceptions;

import java.sql.Timestamp;

public class DtoParsingException extends RuntimeException {

		private static final long serialVersionUID = 1L;
		private Timestamp timestamp ;
		private String message;
		private String details;
		
		

		public DtoParsingException() {}
		public DtoParsingException(String message) {
			this.message = message;
			this.timestamp = new Timestamp(System.currentTimeMillis());
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


