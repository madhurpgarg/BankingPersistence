package com.inflow.banking.repository.hibernate;

import org.hibernate.Session;

import com.inflow.banking.domain.AccountHolder;
import com.inflow.banking.repository.AccountHolderRepository;
import com.inflow.tutorial.util.HibernateUtil;

public class AccountHolderRepositoryImpl implements AccountHolderRepository {
    
    
    
    @Override
    public AccountHolder create(AccountHolder accountHolder){
	
	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	session.beginTransaction();
	
	session.save(accountHolder);
	
	return accountHolder;
    }
    
    @Override
    public AccountHolder delete(String accountHolderId) {
	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	session.beginTransaction();
	
	AccountHolder accountHolder = findById(accountHolderId);
	session.delete(accountHolder);
	
	return accountHolder;
    }
    
    
    @Override
    public AccountHolder findById(String accountHolderId){
	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	session.beginTransaction();
	
	AccountHolder accountHolder = (AccountHolder)session.get(AccountHolder.class, Long.valueOf(accountHolderId));
	
	return accountHolder;
    }
    
    @Override
    public void completeOperation(){
	HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
	HibernateUtil.getSessionFactory().getCurrentSession().close();
    }

}
