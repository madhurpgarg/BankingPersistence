package com.inflow.banking.domain;

import javax.persistence.Entity;


@Entity
public class CurrentAccount extends AbstractAccount {

    CurrentAccount() {}
    
    @Override
    public double  calculateInterest() {
	return this.interestCalculator.calculateInterest(balance);
    }

}
