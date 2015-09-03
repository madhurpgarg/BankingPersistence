package com.inflow.banking.domain;


import org.hibernate.Session;

import com.inflow.tutorial.util.HibernateUtil;

public class TestClient {

    /**
     * @param args
     */
    public static void main(String[] args) {
	
	//createNewAccount();
	//getAccountHolderDetails("1");
	testAssert("Madhur");
	
    }
    
    private static void testAssert(String name) {
	assert name == "Madhur" : "null";
	System.err.println("error");
    }

    public static void createNewAccount() {
	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	session.beginTransaction();
	
	AccountHolder accountHolder = new AccountHolder("Madhur", "Garg");
	
	Address address = new Address();
	address.setCity("Pune");
	address.setCountry("India");
	address.setHouseNumber("204");
	address.setLocality("Viman Nagar");
	address.setStreetName("Viman Nagar");
	address.setState("Maharashtra");
	address.setZipCode("411014");
	
	ContactInfo contact = new ContactInfo(address, "9765407310");
	contact.addPhoneNumber("9595734646");
	contact.addPhoneNumber("9826468434");
	
	accountHolder.setContactInfo(contact);
	
	AbstractAccount account = AccountFactory.getAccount(AccountType.SAVING);
	//accountHolder.addAccount(account);
	
	account.setAccountHolder(accountHolder);
	
	session.save(accountHolder);
	session.getTransaction().commit();
    }
    
    private static void getAccountHolderDetails(String accountNumber) {
	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	session.beginTransaction();
	
	//Loading object from Database with Account Number
	AbstractAccount savingAccount = (AbstractAccount)session.load(AbstractAccount.class, Long.valueOf(accountNumber));
	
	//Retrieving Account Holder's details from account 
	AccountHolder accountHolder = savingAccount.getAccountHolderDetails();
	
	System.err.println("Name: " + accountHolder.getName());
	System.err.println("Primary Phone Number: " + accountHolder.getContactInfo().getPrimaryPhoneNumber());
	
	//Changing the Primary Phone Number
	//accountHolder.getContactInfo().setprimaryPhoneNumber("9595734646");
	
	//System.err.println("Primary Phone Number: " + accountHolder.getContactInfo().getPrimaryPhoneNumber());
	
	//Creating a new Current Account
	AbstractAccount currentAccount = AccountFactory.getAccount(AccountType.CURRENT);
	
	//Allocating a account holder to the above created account
	currentAccount.setAccountHolder(accountHolder);
	
	
	System.err.println("Nuumber of accounts: " + accountHolder.getAllAccounts().size());
	for (AbstractAccount account : accountHolder.getAllAccounts()) {
	    System.err.println(account.getAccountNumber());
	}
	
	session.getTransaction().commit();
    }

}
