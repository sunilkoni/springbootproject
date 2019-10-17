package com.company.exceptions;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CompanyException extends ResponseEntityExceptionHandler{

		@ExceptionHandler(Exception.class)
		public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) throws Exception {
			DefaultException de = new DefaultException(new Date(), ex.getMessage(), request.getDescription(false));
			return new ResponseEntity(de, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	
	
	  @ExceptionHandler(UserNotFoundException.class) public final
	  ResponseEntity<Object> handleUserNotFoundlException(Exception ex, WebRequest
	  request) throws Exception { DefaultException de = new DefaultException(new
	  Date(), ex.getMessage(), request.getDescription(false)); return new
	  ResponseEntity(de, HttpStatus.NOT_FOUND); }
	  
	  @Override
	  protected ResponseEntity<Object> handleMethodArgumentNotValid(
				MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		    DefaultException de = new DefaultException(new Date(), ex.getMessage(), ex.getBindingResult().toString());
			return new ResponseEntity(de, HttpStatus.BAD_REQUEST);
		}
	 
}
