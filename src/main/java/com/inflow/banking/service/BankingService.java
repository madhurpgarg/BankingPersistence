package com.inflow.banking.service;

import java.util.List;

import com.inflow.banking.domain.AbstractAccount;
import com.inflow.banking.domain.AccountHolder;

public interface BankingService {
    
    public AbstractAccount createAccount(AccountHolder accountHolder, AbstractAccount account);
    public AbstractAccount deleteAccount(String accountAccount);
    public AbstractAccount findAccount(String accountNumber);
    public List<AbstractAccount> findAccounts();
    public AccountService getAccountService();
    
}
