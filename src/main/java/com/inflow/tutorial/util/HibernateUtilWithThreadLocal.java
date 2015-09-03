package com.inflow.tutorial.util;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

import com.inflow.banking.exception.InfrastructureException;

public class HibernateUtilWithThreadLocal {
    /*private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
	try {
	    // Create the SessionFactory from hibernate.cfg.xml
	    return new AnnotationConfiguration().configure().buildSessionFactory();
	} catch (Throwable ex) {
	    // Make sure you log the exception, as it might be swallowed
	    System.err.println("Initial SessionFactory creation failed." + ex);
	    throw new ExceptionInInitializerError(ex);
	}
    }*/

    public static SessionFactory getSessionFactory() {
	return sessionFactory;
    }
    
    public static Session getCurrentSession(){
	return sessionFactory.getCurrentSession();
    }
    
    private static final SessionFactory sessionFactory;
    private static final ThreadLocal<Session> threadSession = new ThreadLocal<Session>();
    private static final ThreadLocal<Transaction> threadTransaction = new ThreadLocal<Transaction>();
	    
    static {
	try {
	    sessionFactory = new AnnotationConfiguration().configure()
		    .buildSessionFactory();
	} catch (Throwable ex) {
	    ex.printStackTrace(System.out);
	    throw new ExceptionInInitializerError(ex);
	}
    }

    public static Session getSession() throws HibernateException {
	Session s = (Session) threadSession.get();
	// Open a new Session, if this thread has none yet
	try {
	    if (s == null) {
		s = sessionFactory.openSession();
		threadSession.set(s);
	    }
	} catch (HibernateException ex) {
	    throw new InfrastructureException(ex);
	}
	return s;
    }
    
    public static void closeSession() {
	try {
	    Session s = (Session) threadSession.get();
	    threadSession.set(null);
	    if (s != null && s.isOpen())
		s.close();
	} catch (HibernateException ex) {
	    throw new InfrastructureException(ex);
	}
    }

    public static void beginTransaction() {
	/*Transaction tx = (Transaction) threadTransaction.get();
	try {
	    if (tx == null) {
		tx = getSession().beginTransaction();
		threadTransaction.set(tx);
	    }
	} catch (HibernateException ex) {
	    throw new InfrastructureException(ex);
	}*/
	
	if (getCurrentSession().getTransaction()== null) {
	    getCurrentSession().beginTransaction();
	}
    }
    
    public static void commitTransaction() {
	Transaction tx = (Transaction) threadTransaction.get();
	try {
	    if (tx != null && !tx.wasCommitted() && !tx.wasRolledBack())
		tx.commit();
	    threadTransaction.set(null);
	} catch (HibernateException ex) {
	    rollbackTransaction();
	    throw new InfrastructureException(ex);
	}
    }

    public static void rollbackTransaction() {
	Transaction tx = (Transaction) threadTransaction.get();
	try {
	    threadTransaction.set(null);
	    if (tx != null && !tx.wasCommitted() && !tx.wasRolledBack()) {
		tx.rollback();
	    }
	} catch (HibernateException ex) {
	    throw new InfrastructureException(ex);
	} finally {
	    closeSession();
	}
    }
}
