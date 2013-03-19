package org.jbserv.mail.persistence;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.EmptyInterceptor;
import org.hibernate.Transaction;
import org.hibernate.type.Type;
import org.jbserv.mail.persistence.entity.BaseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HistoryLogInterceptor extends EmptyInterceptor {

	private static final long serialVersionUID = 2700665904938546412L;

	private static final Logger LOG = LoggerFactory
			.getLogger(HistoryLogInterceptor.class);

	private int updates;
	private int creates;
	private int loads;

	@Override
	public void onDelete(Object entity, Serializable id, Object[] state,
			String[] propertyNames, Type[] types) {
		LOG.info("Deleted entity: " + entity);
	}

	@Override
	public boolean onFlushDirty(Object entity, Serializable id,
			Object[] currentState, Object[] previousState,
			String[] propertyNames, Type[] types) {

		if (entity instanceof BaseEntity) {
			updates++;
			for (int i = 0; i < propertyNames.length; i++) {
				if ("updated".equals(propertyNames[i])) {
					currentState[i] = new Date();
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean onLoad(Object entity, Serializable id, Object[] state,
			String[] propertyNames, Type[] types) {
		if (entity instanceof BaseEntity) {
			LOG.info("Loaded entity: " + entity);
			loads++;
		}
		return false;
	}

	@Override
	public boolean onSave(Object entity, Serializable id, Object[] state,
			String[] propertyNames, Type[] types) {

		if (entity instanceof BaseEntity) {
			LOG.info("Created entity: " + entity);
			creates++;
			for (int i = 0; i < propertyNames.length; i++) {
				if ("created".equals(propertyNames[i])) {
					state[i] = new Date();
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public void afterTransactionCompletion(Transaction tx) {
		if (tx.wasCommitted()) {
			LOG.info("Creations: " + creates + ", Updates: " + updates,
					"Loads: " + loads);
		}
		updates = 0;
		creates = 0;
		loads = 0;
	}

}
