package org.jbserv.mail.persistence.repository;

import static org.jbserv.mail.persistence.InitSessionFactory.currentSession;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Transaction;
import org.jbserv.mail.data.model.Account;
import org.jbserv.mail.data.model.Mailbox;
import org.jbserv.mail.data.repository.MailboxRepository;
import org.jbserv.mail.persistence.entity.AccountEntity;
import org.jbserv.mail.persistence.entity.MailboxEntity;
import org.jbserv.mail.persistence.transformer.MailboxTransformer;

public class MailboxRepositoryImpl implements MailboxRepository {

	@Override
	public Mailbox create(Mailbox mailbox) {
		Transaction tx = currentSession().beginTransaction();
		String accountQuery = "SELECT a FROM AccountEntity a WHERE a.username = '"
				+ mailbox.getAccount().getUsername() + "'";
		AccountEntity accountEntity = (AccountEntity) currentSession()
				.createQuery(accountQuery).uniqueResult();
		MailboxEntity mailboxEntity = null;
		if (accountEntity != null) {
			String name = mailbox.getName();
			mailboxEntity = new MailboxEntity();
			mailboxEntity.setAccount(accountEntity);
			mailboxEntity.setName(name);
			mailboxEntity.setUidNext(1);
			currentSession().persist(mailboxEntity);
		}
		tx.commit();
		return MailboxTransformer.transform(mailboxEntity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Mailbox> getAll() {
		Transaction tx = currentSession().beginTransaction();
		String query = "SELECT m FROM MailboxEntity m";
		List<MailboxEntity> result = currentSession().createQuery(query).list();
		List<Mailbox> mailboxes = new ArrayList<>();
		for (MailboxEntity entity : result) {
			Mailbox mailbox = MailboxTransformer.transform(entity);
			mailboxes.add(mailbox);
		}
		tx.commit();
		return mailboxes;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Mailbox> getMailboxesForAccount(Account account) {
		Transaction tx = currentSession().beginTransaction();
		String query = "SELECT m FROM MailboxEntity m WHERE m.account.username = '"
				+ account.getUsername() + "'";
		List<MailboxEntity> result = currentSession().createQuery(query).list();
		List<Mailbox> mailboxes = new ArrayList<>();
		for (MailboxEntity entity : result) {
			Mailbox mailbox = MailboxTransformer.transform(entity);
			mailboxes.add(mailbox);
		}
		tx.commit();
		return mailboxes;
	}

	@Override
	public Mailbox getMailboxByUIDValidity(long uidValidity) {
		Transaction tx = currentSession().beginTransaction();
		MailboxEntity entity = (MailboxEntity) currentSession().get(
				MailboxEntity.class, uidValidity);
		tx.commit();
		return MailboxTransformer.transform(entity);
	}

	@Override
	public Mailbox update(Mailbox mailbox) {
		Transaction tx = currentSession().beginTransaction();
		String query = "SELECT m FROM MailboxEntity m WHERE m.uidValidity = "
				+ mailbox.getUIDValidity();
		MailboxEntity entity = (MailboxEntity) currentSession().createQuery(
				query).uniqueResult();
		if (entity != null) {
			String name = mailbox.getName();
			entity.setName(name);
			currentSession().saveOrUpdate(entity);
		}
		tx.commit();
		return MailboxTransformer.transform(entity);
	}

	@Override
	public void delete(Mailbox mailbox) {
		Transaction tx = currentSession().beginTransaction();
		String query = "SELECT m FROM MailboxEntity m WHERE m.uidValidity = "
				+ mailbox.getUIDValidity();
		MailboxEntity entity = (MailboxEntity) currentSession().createQuery(
				query).uniqueResult();
		if (entity != null) {
			String name = mailbox.getName();
			entity.setName(name);
			currentSession().delete(entity);
		}
		tx.commit();
	}

	@Override
	public Mailbox getMailboxByAccountAndName(Account account, String name) {
		Transaction tx = currentSession().beginTransaction();
		String query = "SELECT m FROM MailboxEntity m WHERE m.account.username = '"
				+ account.getUsername() + "' AND m.name = '" + name + "'";
		MailboxEntity entity = (MailboxEntity) currentSession().createQuery(
				query).uniqueResult();
		tx.commit();
		return MailboxTransformer.transform(entity);
	}

	@Override
	public Mailbox create(Account account, String name) {
		Transaction tx = currentSession().beginTransaction();
		String accountQuery = "SELECT a FROM AccountEntity a WHERE a.username = '"
				+ account.getUsername() + "'";
		AccountEntity accountEntity = (AccountEntity) currentSession()
				.createQuery(accountQuery).uniqueResult();
		MailboxEntity mailboxEntity = null;
		if (accountEntity != null) {
			mailboxEntity = new MailboxEntity();
			mailboxEntity.setAccount(accountEntity);
			mailboxEntity.setName(name);
			mailboxEntity.setUidNext(1);
			currentSession().persist(mailboxEntity);
		}
		tx.commit();
		return MailboxTransformer.transform(mailboxEntity);
	}

}
