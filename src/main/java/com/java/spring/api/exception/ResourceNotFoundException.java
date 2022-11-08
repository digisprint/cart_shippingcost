package com.java.spring.api.exception;


public class ResourceNotFoundException extends Exception {

	private static final long serialVersionUID = 6141754114666186761L;

	public ResourceNotFoundException(String errorMessage) {
		super(errorMessage);
	}

	public ResourceNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public ResourceNotFoundException(Throwable cause) {
		super(cause);
	}

	protected ResourceNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
