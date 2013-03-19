package org.jbserv.mail.persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public final class InitSessionFactory {

	private static SessionFactory sessionFactory;
	private static ServiceRegistry serviceRegistry;

	private InitSessionFactory() {

	}

	static {
		final Configuration cfg = new Configuration();
		cfg.setInterceptor(new HistoryLogInterceptor());
		cfg.configure("/hibernate.cfg.xml");
		serviceRegistry = new ServiceRegistryBuilder().applySettings(
				cfg.getProperties()).buildServiceRegistry();
		sessionFactory = cfg.buildSessionFactory(serviceRegistry);
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static Session currentSession() {
		return getSessionFactory().getCurrentSession();
	}

}
