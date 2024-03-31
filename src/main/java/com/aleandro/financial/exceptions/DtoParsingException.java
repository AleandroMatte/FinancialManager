package com.aleandro.financial.exceptions;

import java.sql.Timestamp;

public class DtoParsingException extends BaseException {

		private static final long serialVersionUID = 1L;
		
		

		public DtoParsingException() {}
		public DtoParsingException(String message) {
			this.message = message;
			this.timestamp = new Timestamp(System.currentTimeMillis());
		}


		

	}


