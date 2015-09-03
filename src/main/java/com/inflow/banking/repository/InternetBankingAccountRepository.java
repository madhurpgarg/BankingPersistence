package com.inflow.banking.repository;

import com.inflow.banking.domain.InternetBankingAccount;

public interface InternetBankingAccountRepository {

    /*boolean addSameBankAccount(String accountNumber, AbstractAccount account);

    boolean addBeneficiaryAccount(String accountNumber,
	    BeneficiaryAccount account);

    double transferAmountToSameBankAccount(String fromAccountNumber,
	    AbstractAccount toAccount, double amount);

    double transferAmountToBeneficiaryAccount(String fromAccountNumber,
	    BeneficiaryAccount toAccount, double amount);*/
    
    public InternetBankingAccount create(InternetBankingAccount internetBankingAccount);
    public InternetBankingAccount delete(String internetBankingAccountId);
    public InternetBankingAccount findById(String internetBankingAccountId);
    public void completeOperation();

}
