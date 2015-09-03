package com.inflow.banking.service;

import java.util.Set;

import com.inflow.banking.domain.AbstractAccount;
import com.inflow.banking.domain.BankingTransaction;
import com.inflow.banking.domain.BeneficiaryAccount;

public interface InternetBankingService {
    
    public boolean addSameBankAccount(String accountNumber, String accountToBeAdded);
    public boolean addBeneficiaryAccount(String accountNumber, BeneficiaryAccount account);
    public double transferAmountToSameBankAccount(String fromAccountNumber, AbstractAccount toAccount, double amount);
    public double transferAmountToBeneficiaryAccount(String fromAccountNumber, BeneficiaryAccount toAccount, double amount);
    public Set<BankingTransaction> getTransactions(String accountNumber);
    public double getBalance(String accountNumber);

}
