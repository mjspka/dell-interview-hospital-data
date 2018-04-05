package com.pmalla.exception;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is a POJO to store REST errors for reporting to the caller.
 * 
 * @author Praveen Malla
 *
 */
public class ErrorResponse {

	private final int status;
	private final String message;
	private List<String> errors = new ArrayList<>();

	private static Logger logger = LoggerFactory.getLogger(ErrorResponse.class);

	public ErrorResponse(int status, String message) {
		this.status = status;
		this.message = message;
	}

	public int getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

	public void addError(String error) {
		logger.error(error);
		errors.add(error);
	}

}
