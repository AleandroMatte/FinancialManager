package com.aleandro.financial.exceptions.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.aleandro.financial.exceptions.DataNotFoundException;
import com.aleandro.financial.exceptions.DebtAlreadyPaidException;
import com.aleandro.financial.exceptions.ExceptionResponse;
import com.aleandro.financial.exceptions.InvalidJwtAuthException;
import com.aleandro.financial.exceptions.UnsupportedPathVariable;
import com.aleandro.financial.exceptions.UserAlreadyExistsException;

import io.jsonwebtoken.ExpiredJwtException;

@ControllerAdvice
@RestController
public class CustomizedExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionResponse> handleAllExceptions(
			Exception ex, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(
				new Date(),ex.getMessage(),request.getDescription(false));
		return new ResponseEntity<>(exceptionResponse,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(UnsupportedPathVariable.class)
	public final ResponseEntity<ExceptionResponse> handleUnsupportedPathVariables(
			Exception ex, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(
				new Date(),ex.getMessage(),request.getDescription(false));
		return new ResponseEntity<>(exceptionResponse,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@ExceptionHandler(DataNotFoundException.class)
	public final ResponseEntity<ExceptionResponse> handleDataNotFoundException(
			Exception ex, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(
				new Date(),ex.getMessage(),request.getDescription(false));
		return new ResponseEntity<>(exceptionResponse,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@ExceptionHandler(InvalidJwtAuthException.class)
	public final ResponseEntity<ExceptionResponse> InvalidJwtAuthException(
			Exception ex, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(
				new Date(),ex.getMessage(),request.getDescription(false));
		return new ResponseEntity<>(exceptionResponse,HttpStatus.FORBIDDEN);
	}
	
	@ExceptionHandler(UserAlreadyExistsException.class)
	public final ResponseEntity<ExceptionResponse> UserAlreadyExistsException(
			Exception ex, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(
				new Date(),ex.getMessage(),request.getDescription(false));
		return new ResponseEntity<>(exceptionResponse,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(ExpiredJwtException.class)
	public final ResponseEntity<ExceptionResponse> ExpiredJwtException(
			Exception ex, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(
				new Date(),ex.getMessage(),request.getDescription(false));
		return new ResponseEntity<>(exceptionResponse,HttpStatus.UNAUTHORIZED);
	}
	@ExceptionHandler(DebtAlreadyPaidException.class)
	public final ResponseEntity<ExceptionResponse> DebtAlreadyPaidException(
			Exception ex, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(
				new Date(),ex.getMessage(),request.getDescription(false));
		return new ResponseEntity<>(exceptionResponse,HttpStatus.BAD_REQUEST);
	}
	
	
	

	

}
