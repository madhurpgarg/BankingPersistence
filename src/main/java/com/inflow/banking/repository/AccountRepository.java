package com.inflow.banking.repository;

import java.util.List;

import com.inflow.banking.domain.AbstractAccount;
import com.inflow.banking.exception.AccountNotFoundException;

public interface AccountRepository {
    
    public AbstractAccount createAccount(AbstractAccount account);
    
    public AbstractAccount deleteAccount(AbstractAccount account);
    
    public AbstractAccount updateAccount(AbstractAccount account);
    
    public AbstractAccount findAccount(String accountNumber) throws AccountNotFoundException;
    
    public List<AbstractAccount> findAllAccounts();
    
    public void completeOperation();
    
}
