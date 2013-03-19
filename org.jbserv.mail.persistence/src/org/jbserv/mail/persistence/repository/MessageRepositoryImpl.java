package org.jbserv.mail.persistence.repository;

import static org.jbserv.mail.persistence.InitSessionFactory.currentSession;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Transaction;
import org.jbserv.mail.data.model.Mailbox;
import org.jbserv.mail.data.model.Message;
import org.jbserv.mail.data.repository.MessageRepository;
import org.jbserv.mail.persistence.entity.MailboxEntity;
import org.jbserv.mail.persistence.entity.MessageEntity;
import org.jbserv.mail.persistence.transformer.MessageTransformer;

public class MessageRepositoryImpl implements MessageRepository {

	@Override
	public Message create(Message message) {
		Transaction tx = currentSession().beginTransaction();
		String mailboxQuery = "SELECT m FROM MailboxEntity m WHERE m.uidValidity = "
				+ message.getMailbox().getUIDValidity();
		MailboxEntity mailboxEntity = (MailboxEntity) currentSession()
				.createQuery(mailboxQuery).uniqueResult();
		MessageEntity messageEntity = null;
		if (mailboxEntity != null) {
			byte[] bodyText = message.getBodyText();
			String from = message.getFrom();
			boolean seen = message.isSeen();
			boolean answered = message.isAnswered();
			boolean flagged = message.isFlagged();
			boolean deleted = message.isDeleted();
			boolean draft = message.isDraft();
			boolean recent = true;
			messageEntity = new MessageEntity();
			messageEntity.setBodyText(bodyText);
			messageEntity.setFrom(from);
			messageEntity.setSeen(seen);
			messageEntity.setAnswered(answered);
			messageEntity.setFlagged(flagged);
			messageEntity.setDeleted(deleted);
			messageEntity.setDraft(draft);
			messageEntity.setRecent(recent);
			currentSession().persist(messageEntity);
		}
		tx.commit();
		return MessageTransformer.transform(messageEntity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Message> getAll() {
		Transaction tx = currentSession().beginTransaction();
		String query = "SELECT m FROM MessageEntity m";
		List<MessageEntity> result = currentSession().createQuery(query).list();
		List<Message> messages = new ArrayList<>();
		for (MessageEntity entity : result) {
			Message message = MessageTransformer.transform(entity);
			messages.add(message);
		}
		tx.commit();
		return messages;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Message> getMessagesForMailbox(Mailbox mailbox) {
		Transaction tx = currentSession().beginTransaction();
		String query = "SELECT m FROM MessageEntity m WHERE m.mailbox.uidValidity = "
				+ mailbox.getUIDValidity();
		List<MessageEntity> result = currentSession().createQuery(query).list();
		List<Message> messages = new ArrayList<>();
		for (MessageEntity entity : result) {
			Message message = MessageTransformer.transform(entity);
			messages.add(message);
		}
		tx.commit();
		return messages;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Message> getMessagesForAccount(Mailbox mailbox) {
		Transaction tx = currentSession().beginTransaction();
		String query = "SELECT m FROM MessageEntity m WHERE m.mailbox.account.username = '"
				+ mailbox.getAccount().getUsername() + "'";
		List<MessageEntity> result = currentSession().createQuery(query).list();
		List<Message> messages = new ArrayList<>();
		for (MessageEntity entity : result) {
			Message message = MessageTransformer.transform(entity);
			messages.add(message);
		}
		tx.commit();
		return messages;
	}

	@Override
	public Message getMessage(Mailbox mailbox, long uid) {
		Transaction tx = currentSession().beginTransaction();
		String query = "SELECT m FROM MessageEntity m WHERE m.uid = " + uid
				+ " AND m.mailbox.uidValidity = " + mailbox.getUIDValidity();
		MessageEntity entity = (MessageEntity) currentSession().createQuery(
				query).uniqueResult();
		Message message = MessageTransformer.transform(entity);
		tx.commit();
		return message;
	}

	@Override
	public Message update(Message message) {
		Transaction tx = currentSession().beginTransaction();
		String query = "SELECT m FROM MessageEntity m WHERE m.uid = "
				+ message.getUID();
		MessageEntity entity = (MessageEntity) currentSession().createQuery(
				query).uniqueResult();
		if (entity != null) {
			String mailboxQuery = "SELECT m FROM MailboxEntity m WHERE m.uidValidity = "
					+ message.getMailbox().getUIDValidity();
			MailboxEntity mailboxEntity = (MailboxEntity) currentSession()
					.createQuery(mailboxQuery).uniqueResult();
			if (mailboxEntity != null) {
				byte[] bodyText = message.getBodyText();
				String from = message.getFrom();
				boolean seen = message.isSeen();
				boolean answered = message.isAnswered();
				boolean flagged = message.isFlagged();
				boolean deleted = message.isDeleted();
				boolean draft = message.isDraft();
				boolean recent = message.isRecent();
				entity.setBodyText(bodyText);
				entity.setFrom(from);
				entity.setSeen(seen);
				entity.setAnswered(answered);
				entity.setFlagged(flagged);
				entity.setDeleted(deleted);
				entity.setDraft(draft);
				entity.setRecent(recent);
				currentSession().saveOrUpdate(entity);
			}
		}
		tx.commit();
		return MessageTransformer.transform(entity);
	}

	@Override
	public void delete(Message message) {
		Transaction tx = currentSession().beginTransaction();
		String query = "SELECT m FROM MessageEntity m WHERE m.uid = "
				+ message.getUID();
		MessageEntity entity = (MessageEntity) currentSession().createQuery(
				query).uniqueResult();
		if (entity != null) {
			currentSession().delete(entity);
		}
		tx.commit();
	}

	@Override
	public Message create(Mailbox mailbox, String from, byte[] bodyText) {
		Transaction tx = currentSession().beginTransaction();
		String mailboxQuery = "SELECT m FROM MailboxEntity m WHERE m.uidValidity = "
				+ mailbox.getUIDValidity();
		MailboxEntity mailboxEntity = (MailboxEntity) currentSession()
				.createQuery(mailboxQuery).uniqueResult();
		MessageEntity messageEntity = null;
		if (mailboxEntity != null) {
			boolean seen = false;
			boolean answered = false;
			boolean flagged = false;
			boolean deleted = false;
			boolean draft = false;
			boolean recent = true;
			messageEntity = new MessageEntity();
			messageEntity.setBodyText(bodyText);
			messageEntity.setFrom(from);
			messageEntity.setSeen(seen);
			messageEntity.setAnswered(answered);
			messageEntity.setFlagged(flagged);
			messageEntity.setDeleted(deleted);
			messageEntity.setDraft(draft);
			messageEntity.setRecent(recent);
			currentSession().persist(messageEntity);
		}
		tx.commit();
		return MessageTransformer.transform(messageEntity);
	}

}
