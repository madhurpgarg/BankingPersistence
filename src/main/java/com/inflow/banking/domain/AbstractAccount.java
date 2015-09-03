package com.inflow.banking.domain;

import java.util.Currency;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.inflow.banking.exception.InsufficientBalanceException;

@Entity
@Table(name = "Account")
public abstract class AbstractAccount {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long accountNumber;
	protected Currency currency;
	protected double balance;
	protected Date accountCreationDate;

	@OneToMany(targetEntity = BankingTransaction.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	protected Set<BankingTransaction> bankingTransactions;

	@ManyToOne(targetEntity = AccountHolder.class)
	@JoinColumn(name = "account_holder_id")
	protected AccountHolder accountHolder;

	@Transient
	protected InterestCalculator interestCalculator;
	@ManyToOne(targetEntity = InternetBankingAccount.class)
	protected InternetBankingAccount netBankingAccount;

	protected AbstractAccount() {
		this.currency = Currency.getInstance(new Locale("hi", "IN"));
		this.bankingTransactions = new HashSet<BankingTransaction>();
		this.accountCreationDate = new Date();
	}

	public String getAccountNumber() {
		return String.valueOf(this.accountNumber);
	}

	public double credit(double amountToCredit) {
		this.balance = this.balance + amountToCredit;
		BankingTransaction bt = new BankingTransaction(new Date(),
				amountToCredit, "Deposited by some channel",
				TransactionType.CREDIT);
		bankingTransactions.add(bt);
		return this.balance;
	}

	/**
	 * @description Deduct the amount from Balance Amount
	 * @param amount
	 *            to be deducted from account
	 * @return current balance after deduction
	 * @throws IllegalArgumentException
	 *             if the amount is zero or less than zero i.e negative number
	 * @throws InsufficientBalanceException
	 *             if the account doesn't have sufficient balance
	 */
	public double debit(double amountTodebit) {
		if (amountTodebit <= 0) {
			throw new IllegalArgumentException(
					"Amount to be debited should be positive number other than zero but it is "
							+ amountTodebit);
		}
		if (this.balance < amountTodebit) {
			throw new InsufficientBalanceException();
		}
		this.balance = this.balance - amountTodebit;
		BankingTransaction bt = new BankingTransaction(new Date(),
				amountTodebit, "Withdraw by some channel",
				TransactionType.DEBIT);
		bankingTransactions.add(bt);
		return this.balance;
	}

	public double getAvailableBalance() {
		return this.balance;
	}

	protected void setOpeningBalance(double amount) {
		this.balance = amount;
	}

	public void setInterestCalculator(InterestCalculator interestCalculator) {
		if (interestCalculator == null) {
			throw new NullPointerException(
					"Can not be null, should be a valid interest calculator");
		}
		this.interestCalculator = interestCalculator;
	}

	protected abstract double calculateInterest();

	public Date getAccountCreationDate() {
		return accountCreationDate;
	}

	public Set<BankingTransaction> getBankingTransactions() {
		return bankingTransactions;
	}

	public AccountHolder getAccountHolderDetails() {
		return accountHolder;

	}

	public void setAccountHolder(AccountHolder accountHolder) {
		if (accountHolder == null) {
			throw new NullPointerException("Account holder can not be null");
		}
		this.accountHolder = accountHolder;
		this.accountHolder.addAccount(this);
	}

	public String getCurrency() {
		return currency.getSymbol();
	}

	public void enableNetBankingService(InternetBankingAccount netBankingAccount) {
		this.netBankingAccount = netBankingAccount;
	}

	public InternetBankingAccount getNetBankingAccount() {
		return this.netBankingAccount;
	}

}
