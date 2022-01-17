package com.abc.vehiclerental.exception;

public class AuthenticationFailureException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AuthenticationFailureException() {

	}

	public AuthenticationFailureException(String message) {
		super(message);
	}
}
