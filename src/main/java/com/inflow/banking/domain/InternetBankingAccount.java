package com.inflow.banking.domain;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class InternetBankingAccount {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;
    
    @OneToOne(targetEntity=AbstractAccount.class)
    @JoinColumn(name="accountNumber")
    private AbstractAccount account;
    
    @OneToMany(targetEntity = BeneficiaryAccount.class,cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<BeneficiaryAccount> beneficiaryAccounts;
    
    @OneToMany(targetEntity = AbstractAccount.class, fetch = FetchType.LAZY)
    //@JoinColumn(name="accountNumber")
    private Set<AbstractAccount> accounts;
    
    InternetBankingAccount() {}

    public InternetBankingAccount(AbstractAccount account) {
	this.account = account;
	BeneficiaryAccount dummyBeneficiaryAccount = new BeneficiaryAccount();
	beneficiaryAccounts = new HashSet<BeneficiaryAccount>();
	beneficiaryAccounts.add(dummyBeneficiaryAccount);
	accounts = new HashSet<AbstractAccount>();
    }

    public boolean addAccount(AbstractAccount account) {
	if (account == null)
	    throw new NullPointerException(
		    "A null account can not be added, please provide a valid account");
	return accounts.add(account);
    }

    public boolean addAccount(BeneficiaryAccount account) {
	if (account == null)
	    throw new NullPointerException(
		    "A null account can not be added, please provide a valid account");
	return beneficiaryAccounts.add(account);
    }

    public double transferAmount(BeneficiaryAccount account,
	    double amountToTransfer) {
	double balance = 0;
	if (account == null)
	    throw new NullPointerException(
		    "Account in which amount is to be transferred can not be null");

	if (amountToTransfer <= 0) {
	    throw new IllegalArgumentException(
		    "Amount to be transferred should be a non-zero and positive value");
	}
	if (!beneficiaryAccounts.contains(account))
	    throw new NoSuchElementException(account.getAccountNumber()
		    + " of " + account.getAccountHolderName()
		    + " is not available for transfering amount");

	balance = this.account.debit(amountToTransfer);
	account.credit(amountToTransfer);
	return balance;
    }

    public double transferAmount(AbstractAccount account,
	    double amountToTransfer) {
	double balance = 0;
	if (account == null)
	    throw new NullPointerException(
		    "Account in which amount is to be transferred can not be null");
	if (amountToTransfer <= 0) {
	    throw new IllegalArgumentException(
		    "Amount to be transferred should be a non-zero and positive value");
	}
	if (!beneficiaryAccounts.contains(account))
	    throw new NoSuchElementException(account.getAccountNumber()
		    + " is not available for transfering amount");
	balance = this.account.debit(amountToTransfer);
	account.credit(amountToTransfer);
	return balance;
    }
}
