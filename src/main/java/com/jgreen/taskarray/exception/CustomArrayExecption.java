package com.jgreen.taskarray.exception;

public class CustomArrayExecption extends Exception {
	private static final long serialVersionUID = 1L;

	public CustomArrayExecption(String message) {
		super(message);
	}
	
	public CustomArrayExecption(String message, Throwable cause) {
		super(message, cause);
	}
}
