package com.inflow.banking.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Transaction")
public class BankingTransaction {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Date date;
	private double amount;
	private String msg;
	private TransactionType transactionType;

	public BankingTransaction() {

	}

	public BankingTransaction(Date date, double amount) {
		this.date = date;
		this.amount = amount;
	}

	public BankingTransaction(Date date, double amount, String msg) {
		this.date = date;
		this.amount = amount;
		this.msg = msg;
	}

	public BankingTransaction(Date date, double amount, String msg,
			TransactionType transactionType) {
		this.date = date;
		this.amount = amount;
		this.msg = msg;
		this.transactionType = transactionType;
	}

	@Override
	public String toString() {
		return this.amount + " | " + this.date.toString() + " | " + this.msg
				+ " | " + this.transactionType;
	}

}
