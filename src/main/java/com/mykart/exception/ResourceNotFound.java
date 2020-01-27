package com.mykart.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND,reason="Resource Not Found")
public class ResourceNotFound extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ResourceNotFound() {
		super();
	}
	public ResourceNotFound(String message) {
		super(message);
	}
	

}
