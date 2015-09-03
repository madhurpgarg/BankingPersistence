package com.inflow.banking.domain;



import org.junit.Before;
import org.junit.Test;

import com.inflow.banking.service.BankingService;
import com.inflow.banking.service.impl.BankingServiceImpl;

public class BankingServiceTest {
    
    private BankingService bankingService;

    @Before
    public void setUp() throws Exception {
	bankingService = new BankingServiceImpl();
    }

    @Test
    public void testCreateAccount() {
	Address address = new Address();
	address.setCity("Pune");
	address.setCountry("India");

	ContactInfo contactInfo = new ContactInfo(address, "9765407310");
	contactInfo.addPhoneNumber("9595734646");

	AccountHolder accountHolder = new AccountHolder("John", "Dio");
	accountHolder.setContactInfo(contactInfo);
	
	AbstractAccount account = AccountFactory.getAccount(AccountType.SAVING);
	account.setAccountHolder(accountHolder);
	
	account = bankingService.createAccount(accountHolder, account);
	System.out.println(account.getAvailableBalance());
	
    }
    
    @Test
    public void testEnableNetBanking(){
	bankingService.getAccountService().enableInternetBankingService("1");
    }

    @Test
    public void testDeleteAccount() {
	AbstractAccount account = bankingService.deleteAccount("5");
	System.out.println(account.getAvailableBalance());
    }

    @Test
    public void testFindAccount() {
	AbstractAccount account = bankingService.findAccount("10");
	System.out.println(account.getAvailableBalance());
	System.out.println(account.getAccountHolderDetails().getName());
    }

    @Test
    public void testWithdraw() {
	System.out.println(bankingService.getAccountService().withdraw("3", 4000));
    }

    @Test
    public void testDeposite() {
	System.out.println(bankingService.getAccountService().deposite("3", 1000));
    }

    @Test
    public void testCurrentBalance() {
	System.out.println(bankingService.getAccountService().currentBalance("3"));
    }
    
    @Test
    public void testGetTransactions(){
	for (BankingTransaction bankingTransaction : bankingService.getAccountService().getAllTransactions("2")) {
	    System.out.println(bankingTransaction);
	}
    }

}
