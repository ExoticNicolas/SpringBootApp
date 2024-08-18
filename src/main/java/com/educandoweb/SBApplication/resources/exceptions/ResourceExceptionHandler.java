package com.educandoweb.SBApplication.resources.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.educandoweb.SBApplication.services.exceptions.DatabaseException;
import com.educandoweb.SBApplication.services.exceptions.ResourceNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> handlerResourceNotFoundException(ResourceNotFoundException ex, HttpServletRequest request){
		String err = "RESOURCE NOT FOUND";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError error = new StandardError(Instant.now(),status.value(),err,ex.getMessage(),request.getRequestURI());
		return ResponseEntity.status(status).body(error);
	}
	
	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<StandardError> handlerResourceDatabaseException(DatabaseException ex, HttpServletRequest request){
		String err = "INTEGRITY VIOLATION";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError error = new StandardError(Instant.now(),status.value(),err,ex.getMessage(),request.getRequestURI());
		return ResponseEntity.status(status).body(error);
	}

}
