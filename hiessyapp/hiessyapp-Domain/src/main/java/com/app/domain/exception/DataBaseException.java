package com.app.domain.exception;

public class DataBaseException extends Exception {

	private static final long serialVersionUID = 1L;
	private String message;
	private Exception e;

	public DataBaseException() {

	}

	public DataBaseException(String message) {
		this.message = message;
	}

	public DataBaseException(String message, Exception e) {
		this.e = e;
		this.message = message;
	}

	@Override
	public String getMessage() {
		return this.message;
	}

	public Exception getE() {
		return this.e;
	}

}
