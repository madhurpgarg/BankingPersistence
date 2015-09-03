package com.inflow.banking.exception;

public class PhoneNumberNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -4224147708857557493L;
    
    public PhoneNumberNotFoundException(String msg) {
	System.err.println(msg);
    }
}
