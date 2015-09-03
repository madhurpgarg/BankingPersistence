package com.inflow.banking.domain;


public class AccountFactory {
    
    public static AbstractAccount getAccount(AccountType accountType){
	AbstractAccount account = null;
	if(accountType.equals(AccountType.SAVING)){
	    account = new SavingAccount();
	    account.setOpeningBalance(5000);
	    account.setInterestCalculator(new SavingAccountInterestCalculator());
	}else if(accountType.equals(AccountType.CURRENT)){
	    account = new CurrentAccount();
	    account.setOpeningBalance(5000);
	    account.setInterestCalculator(new NoInterest());
	}
	
	return account;
    }

}
