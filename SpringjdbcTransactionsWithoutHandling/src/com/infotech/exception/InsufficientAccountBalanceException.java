package com.infotech.exception;

public class InsufficientAccountBalanceException extends Exception {

	private static final long serialVersionUID = 1380349681562411821L;
	
	public InsufficientAccountBalanceException(String message) {
		super(message);
	}
}
