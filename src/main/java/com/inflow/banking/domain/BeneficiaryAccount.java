package com.inflow.banking.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class BeneficiaryAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountNumber;
    private String accountHolderName;
    private String bankName;
    private String isfcCode;
    private Date date;
    
    public BeneficiaryAccount() {}
    
    public BeneficiaryAccount(Long accountNumber, String accountHolderName,
	    String bankName, String isfcCode) {
	this.accountNumber = accountNumber;
	this.accountHolderName = accountHolderName;
	this.bankName = bankName;
	this.isfcCode = isfcCode;
	this.date = new Date();
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public String getBankName() {
        return bankName;
    }

    public String getIsfcCode() {
        return isfcCode;
    }

    public Date getDate() {
        return date;
    }

    public void credit(double amount){
	System.out.println("Call Web Service of the bank");
	System.out.println(amount + "amount transfered to this account" + this);
    }
    
    @Override
    public String toString() {
        return accountNumber + " of " + bankName;
    }
}
