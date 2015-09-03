package com.inflow.banking.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ACCOUNT_HOLDER")
public class AccountHolder {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ACCOUNT_HOLDER_ID")
    private Long acountHolderId;
    @Column(name="FIRST_NAME")
    private String firstName;
    @Column(name="LAST_NAME")
    private String lastName;
    
    @OneToMany(targetEntity = AbstractAccount.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<AbstractAccount> accounts;
    
    @Embedded
    private ContactInfo contactInfo;
    public AccountHolder() {}
    
    public AccountHolder(String firstName, String lastName) {
	this.firstName = firstName;
	this.lastName = lastName;
	this.accounts = new HashSet<AbstractAccount>();
    }
    
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public ContactInfo getContactInfo() {
        return contactInfo;
    }
    public void setContactInfo(ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }
    
    public String getName(){
	return firstName + " " + lastName;
    }
    
    public void addAccount(AbstractAccount account){
	this.accounts.add(account);
    }
    
    public Set<AbstractAccount> getAllAccounts() {
	return accounts;
    }

}
