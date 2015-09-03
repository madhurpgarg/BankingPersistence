package com.inflow.banking.exception;


public class AccountNotFoundException extends RuntimeException {

    public AccountNotFoundException(String message) {
	super(message);
    }

    private static final long serialVersionUID = -7964645197007878457L;

}
