package com.inflow.banking.repository;

import com.inflow.banking.domain.AccountHolder;

public interface AccountHolderRepository {
    
    public AccountHolder create(AccountHolder accountHolder);
    public AccountHolder delete(String accountHolderId);
    public AccountHolder findById(String accountHolderId);
    public void completeOperation();
    
}
