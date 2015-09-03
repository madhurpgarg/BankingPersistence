package com.inflow.banking.service.impl;

import java.util.Set;

import com.inflow.banking.domain.AbstractAccount;
import com.inflow.banking.domain.BankingTransaction;
import com.inflow.banking.domain.InternetBankingAccount;
import com.inflow.banking.repository.AccountRepository;
import com.inflow.banking.repository.InternetBankingAccountRepository;
import com.inflow.banking.repository.hibernate.InternetBankingAccountRepositoryImpl;
import com.inflow.banking.service.AccountService;

public class AccountServiceImpl implements AccountService {

	private AccountRepository accountRepository;
	private InternetBankingAccountRepository internetBankingAccountRepository;

	public AccountServiceImpl(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
		this.internetBankingAccountRepository = new InternetBankingAccountRepositoryImpl();
	}

	/*
	 * @Override public void transferAmount(String fromAccountNumber, String
	 * toAccountNumber, double amount) { AbstractAccount fromAccount =
	 * accountRepository.findAccount(fromAccountNumber); AbstractAccount
	 * toAccount = accountRepository.findAccount(toAccountNumber);
	 * //fromAccount.transferTo(toAccount, amount);
	 * accountRepository.updateAccount(fromAccount);
	 * accountRepository.updateAccount(toAccount);
	 * accountRepository.completeOperation(); }
	 */

	@Override
	public double withdraw(String accountNumber, double amount) {
		AbstractAccount account = this.accountRepository
				.findAccount(accountNumber);
		double balance = account.debit(amount);
		this.accountRepository.completeOperation();
		return balance;
	}

	@Override
	public double deposite(String accountNumber, double amount) {
		AbstractAccount account = this.accountRepository
				.findAccount(accountNumber);
		double balance = account.credit(amount);
		this.accountRepository.completeOperation();
		return balance;
	}

	@Override
	public double currentBalance(String accountNumber) {
		AbstractAccount account = this.accountRepository
				.findAccount(accountNumber);
		double balance = account.getAvailableBalance();
		this.accountRepository.completeOperation();
		return balance;
	}

	@Override
	public Set<BankingTransaction> getAllTransactions(String accountNumber) {
		AbstractAccount account = this.accountRepository
				.findAccount(accountNumber);
		Set<BankingTransaction> bankingTransactions = account
				.getBankingTransactions();
		this.accountRepository.completeOperation();
		return bankingTransactions;
	}

	@Override
	public void enableInternetBankingService(String accountNumber) {
		AbstractAccount account = this.accountRepository
				.findAccount(accountNumber);
		if (account.getNetBankingAccount() != null)
			throw new UnsupportedOperationException(
					"Internet Banking service is already enabled");
		InternetBankingAccount netBankingAccount = new InternetBankingAccount(
				account);
		netBankingAccount = this.internetBankingAccountRepository
				.create(netBankingAccount);
		account.enableNetBankingService(netBankingAccount);
		this.accountRepository.completeOperation();
	}
}
