package com.inflow.banking.service.impl;

import java.util.List;
import java.util.NoSuchElementException;

import com.inflow.banking.domain.AbstractAccount;
import com.inflow.banking.domain.AccountHolder;
import com.inflow.banking.repository.AccountHolderRepository;
import com.inflow.banking.repository.AccountRepository;
import com.inflow.banking.repository.hibernate.AccountHolderRepositoryImpl;
import com.inflow.banking.repository.hibernate.AccountRepositoryImpl;
import com.inflow.banking.service.AccountService;
import com.inflow.banking.service.BankingService;

public class BankingServiceImpl implements BankingService {

    private final AccountRepository accountRepository;
    private final AccountHolderRepository accountHolderRepository;
    private final AccountService accountService;

    public BankingServiceImpl() {
	accountRepository = new AccountRepositoryImpl();
	accountHolderRepository = new AccountHolderRepositoryImpl();
	accountService = new AccountServiceImpl(accountRepository);
    }

    @Override
    public AbstractAccount createAccount(AccountHolder accountHolder, AbstractAccount account) {
	
	accountHolderRepository.create(accountHolder);
	
	accountRepository.createAccount(account);
	accountRepository.completeOperation();
	
	return account;
    }

    @Override
    public AbstractAccount deleteAccount(String accountNumber) {
	return accountRepository.deleteAccount(accountRepository
		.findAccount(accountNumber));
    }

    @Override
    public AbstractAccount findAccount(String accountNumber) {
	AbstractAccount account = null;
	account = accountRepository.findAccount(accountNumber);
	if (account == null) {
	    throw new NoSuchElementException("Account not found in repository");
	}
	return account;
    }

    @Override
    public AccountService getAccountService() {
	return accountService;
    }

    @Override
    public List<AbstractAccount> findAccounts() {
	return accountRepository.findAllAccounts();
    }

}
