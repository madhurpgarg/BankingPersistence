package com.inflow.banking.repository.hibernate;

import java.util.List;

import org.hibernate.Session;

import com.inflow.banking.domain.AbstractAccount;
import com.inflow.banking.domain.SavingAccount;
import com.inflow.banking.exception.AccountNotFoundException;
import com.inflow.banking.repository.AccountRepository;
import com.inflow.tutorial.util.HibernateUtil;

public class AccountRepositoryImpl implements AccountRepository {
    
    @Override
    public AbstractAccount createAccount(AbstractAccount account){
	
	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	session.beginTransaction();
	
	session.save(account);
	
	return account;
    }
    
    @Override
    public AbstractAccount deleteAccount(AbstractAccount account){
	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	session.beginTransaction();
	
	session.delete(account);
	
	return account;
    }
    
    @Override
    public AbstractAccount updateAccount(AbstractAccount account){
	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	session.beginTransaction();
	
	session.update(account);
	
	return account;
    }
    
    @Override
    public AbstractAccount findAccount(String accountNumber) throws AccountNotFoundException {
	SavingAccount savingAccount = null;
	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	session.beginTransaction();
	    savingAccount = (SavingAccount) session.get(SavingAccount.class,
		    Long.valueOf(accountNumber));
	return savingAccount;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public List<AbstractAccount> findAllAccounts() {
	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	session.beginTransaction();
	
	return session.createQuery("FROM AbstractAccount").list();
    }
    
    @Override
    public void completeOperation(){
	HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
	HibernateUtil.getSessionFactory().getCurrentSession().close();
    }

}
