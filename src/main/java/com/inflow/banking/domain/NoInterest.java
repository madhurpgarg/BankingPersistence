package com.inflow.banking.domain;

import com.inflow.banking.exception.IneligibleAccountForInterest;

public class NoInterest implements InterestCalculator {

    @Override
    public double calculateInterest(double currentBalance) {
	throw new IneligibleAccountForInterest();
    }

}
