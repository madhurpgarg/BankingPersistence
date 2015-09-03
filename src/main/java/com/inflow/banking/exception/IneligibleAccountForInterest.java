package com.inflow.banking.exception;

public class IneligibleAccountForInterest extends RuntimeException {

    private static final long serialVersionUID = 8789894304141986731L;
    
    public IneligibleAccountForInterest() {
	super("This account is not eligible for interest");
    }

}
