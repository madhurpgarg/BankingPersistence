package com.inflow.banking.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.JoinColumn;

import com.inflow.banking.exception.PhoneNumberNotFoundException;

@Embeddable
public class ContactInfo {
    
    @Embedded
    private Address address;
    @ElementCollection
    @CollectionTable(name="PHONE_NUMBER", joinColumns=@JoinColumn(name="ACCOUNT_HOLDER_ID"))
    @Column(name="NUMBER")
    private Set<String> phoneNumbers;
    /**
     * This will one of the numbers available in phoneNumbers Set
     */
    
    @Column(name = "PRIMARY_PHONE_NUMBER")
    private String primaryPhoneNumber;
    
    ContactInfo() {}
    
    public ContactInfo(Address address, String phoneNumber) {
	this.address = address;
	
	if(phoneNumbers== null){
	    phoneNumbers = new HashSet<String>();
	}
	
	phoneNumbers.add(phoneNumber);
	primaryPhoneNumber = phoneNumber;
    }
    
    public void addPhoneNumber(String phoneNumber){
	phoneNumbers.add(phoneNumber);
    }
    
    public Address getAddress(){
	return address;
    }
    
    public String getPrimaryPhoneNumber(){
	return primaryPhoneNumber;
    }
    
    public void setprimaryPhoneNumber(String primaryPhoneNumber) {
	if(phoneNumbers.contains(primaryPhoneNumber))
	    this.primaryPhoneNumber = primaryPhoneNumber;
	else
	    throw new PhoneNumberNotFoundException("Primary Phone Number can only be from Account Holder's Number");
    }
}
