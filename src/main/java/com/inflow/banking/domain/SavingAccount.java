package com.inflow.banking.domain;

import java.util.Date;

import javax.persistence.Entity;

@Entity
public class SavingAccount extends AbstractAccount {

    SavingAccount() {}
    
    public double calculateInterest() {
	double interest = this.interestCalculator.calculateInterest(this.balance);
	BankingTransaction bt = new BankingTransaction(new Date(), interest, "Interest Gained till " + new Date());
	bankingTransactions.add(bt);
	return interest;
    }
}
