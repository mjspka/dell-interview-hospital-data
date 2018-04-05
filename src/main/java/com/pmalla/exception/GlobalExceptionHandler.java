package com.pmalla.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * The purpose of this class is to convert REST errors into system defined
 * defined format for reporting to the caller.
 * 
 * @author Praveen Malla
 *
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
	private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleException(Exception ex) {
		logger.error("System Error", ex);
		ErrorResponse response = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
		if (ex != null && ex.getCause() != null) {
			response.addError(ex.getCause().toString());
		}
		return new ResponseEntity<ErrorResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(NotImplementedException.class)
	public ResponseEntity<?> handleNotImplementedException(NotImplementedException ex) {
		logger.warn(String.format("System warning %s", ex.getMessage()));
		ErrorResponse response = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
		if (ex != null && ex.getCause() != null) {
			response.addError(ex.getCause().toString());
		}
		return new ResponseEntity<ErrorResponse>(response, HttpStatus.BAD_REQUEST);
	}

}
