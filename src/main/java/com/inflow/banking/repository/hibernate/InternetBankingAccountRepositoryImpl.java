package com.inflow.banking.repository.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.inflow.banking.domain.InternetBankingAccount;
import com.inflow.banking.exception.AccountNotFoundException;
import com.inflow.banking.repository.InternetBankingAccountRepository;
import com.inflow.tutorial.util.HibernateUtil;

public class InternetBankingAccountRepositoryImpl implements
		InternetBankingAccountRepository {

	@Override
	public InternetBankingAccount create(
			InternetBankingAccount internetBankingAccount) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		if ((Long) session.save(internetBankingAccount) == null) {
			throw new HibernateException(
					"Internet Account could not be created in repository by hibernate");
		}

		return internetBankingAccount;

	}

	@Override
	public InternetBankingAccount delete(String internetBankingAccountId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		InternetBankingAccount netBankingAccount = findById(internetBankingAccountId);

		session.delete(netBankingAccount);
		session.evict(netBankingAccount);

		return netBankingAccount;
	}

	@Override
	public InternetBankingAccount findById(String internetBankingAccountId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		InternetBankingAccount netBankingAccount = (InternetBankingAccount) session
				.get(InternetBankingAccount.class,
						Long.valueOf(internetBankingAccountId));
		if (netBankingAccount == null) {
			throw new AccountNotFoundException(
					"Internet Banking account is not found in repository by hibernate");
		}
		session.evict(netBankingAccount);

		return netBankingAccount;
	}

	@Override
	public void completeOperation() {
		HibernateUtil.getSessionFactory().getCurrentSession().getTransaction()
				.commit();
		HibernateUtil.getSessionFactory().getCurrentSession().close();
	}

}
