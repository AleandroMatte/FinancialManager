package com.aleandro.financial.exceptions;

import java.sql.Timestamp;

public class DebtAlreadyPaidException extends BaseException{
	
	
	
	public DebtAlreadyPaidException() {
		this.message = "debt has already been paid";
		this.timestamp = new Timestamp(System.currentTimeMillis());
	}

}
