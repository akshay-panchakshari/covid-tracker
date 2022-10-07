package com.kn.covid.tracker.exception;

public class AppException extends RuntimeException {

	private static final long serialVersionUID = 5739453122773760764L;

	public AppException(String errorMessage, Throwable err) {
		super(errorMessage, err);
	}

	public AppException(String errorMessage) {
		super(errorMessage);
	}
}
