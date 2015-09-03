package com.inflow.banking.exception;

public class InsufficientBalanceException extends RuntimeException {

    private static final long serialVersionUID = -1863045769078604445L;

    public InsufficientBalanceException() {
	super("Account doesn't have sufficient balance");
    }
}
