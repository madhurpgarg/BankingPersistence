package com.inflow.tutorial.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class HibernateUtil {

    private static final SessionFactory sessionFactory;

    static {
	try {
	    sessionFactory = new AnnotationConfiguration().configure()
		    .buildSessionFactory();
	} catch (Throwable ex) {
	    ex.printStackTrace(System.out);
	    throw new ExceptionInInitializerError(ex);
	}
    }

    public static SessionFactory getSessionFactory() {
	return sessionFactory;
    }

}
