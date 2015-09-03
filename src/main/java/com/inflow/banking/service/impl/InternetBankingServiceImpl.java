package com.inflow.banking.service.impl;

import java.util.Set;

import com.inflow.banking.domain.AbstractAccount;
import com.inflow.banking.domain.BankingTransaction;
import com.inflow.banking.domain.BeneficiaryAccount;
import com.inflow.banking.domain.InternetBankingAccount;
import com.inflow.banking.repository.AccountRepository;
import com.inflow.banking.repository.InternetBankingAccountRepository;
import com.inflow.banking.repository.hibernate.AccountRepositoryImpl;
import com.inflow.banking.repository.hibernate.InternetBankingAccountRepositoryImpl;
import com.inflow.banking.service.AccountService;
import com.inflow.banking.service.InternetBankingService;

public class InternetBankingServiceImpl implements InternetBankingService {

    private InternetBankingAccountRepository internetBankingAccountRepository;
    private AccountRepository accountRepository;
    private AccountService accountService;

    public InternetBankingServiceImpl() {
	this.accountRepository = new AccountRepositoryImpl();
	this.accountService = new AccountServiceImpl(accountRepository);
	this.internetBankingAccountRepository = new InternetBankingAccountRepositoryImpl();
    }

    @Override
    public boolean addSameBankAccount(String accountNumber,
	    String accountNumberOfAccountToBeAdded) {
	boolean status= false;
	AbstractAccount thisAccount = this.accountRepository
		.findAccount(accountNumber);
	AbstractAccount accountToBeAdded = this.accountRepository.findAccount(accountNumberOfAccountToBeAdded);
	
	InternetBankingAccount thisNetBankingAccount = thisAccount.getNetBankingAccount();
	
	if (thisNetBankingAccount == null) {
	    throw new NullPointerException("Internet banking account is not available for " + accountNumber + " Account");
	}
	status = thisNetBankingAccount.addAccount(accountToBeAdded);
	
	this.accountRepository.completeOperation();
	return status;

    }

    @Override
    public boolean addBeneficiaryAccount(String accountNumber,
	    BeneficiaryAccount accountToAdd) {
	boolean status= false;
	AbstractAccount thisAccount = this.accountRepository
		.findAccount(accountNumber);
	
	InternetBankingAccount thisNetBankingAccount = thisAccount.getNetBankingAccount();
	
	if (thisNetBankingAccount == null) {
	    throw new NullPointerException("Internet banking account is null for " + accountNumber + " Account Number");
	}
	status = thisNetBankingAccount.addAccount(accountToAdd);
	
	this.accountRepository.completeOperation();
	return status;
    }

    @Override
    public double transferAmountToSameBankAccount(String fromAccountNumber,
	    AbstractAccount toAccount, double amount) {
		return amount;
    }

    @Override
    public double transferAmountToBeneficiaryAccount(String fromAccountNumber,
	    BeneficiaryAccount toAccount, double amount) {
		return amount;
    }

    @Override
    public Set<BankingTransaction> getTransactions(String accountNumber) {
	return this.accountService.getAllTransactions(accountNumber);
    }

    @Override
    public double getBalance(String accountNumber) {
	return this.accountService.currentBalance(accountNumber);
    }

}
