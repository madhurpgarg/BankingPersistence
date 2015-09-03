package com.inflow.banking.service;

import java.util.Set;

import com.inflow.banking.domain.BankingTransaction;

public interface AccountService {
    //public void transferAmount(String fromAccount, String toAccount, double amount );
    public double withdraw(String accountNumber, double amount );
    public double deposite(String accountNumber, double amount );	
    public double currentBalance(String account);
    public Set<BankingTransaction> getAllTransactions(String accountNumber);
    public void enableInternetBankingService(String accountNumber);
}
