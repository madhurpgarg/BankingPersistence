package com.inflow.banking.domain;

public class SavingAccountInterestCalculator implements InterestCalculator {

    private double interestRate = 9.5;;
    
    @Override
    public double calculateInterest(double currentBalance) {
	return currentBalance * (interestRate/100);
    }

}
