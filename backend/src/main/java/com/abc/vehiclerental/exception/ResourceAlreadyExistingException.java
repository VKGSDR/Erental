package com.abc.vehiclerental.exception;

public class ResourceAlreadyExistingException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ResourceAlreadyExistingException() {

	}

	public ResourceAlreadyExistingException(String message) {
		super(message);
	}
}
