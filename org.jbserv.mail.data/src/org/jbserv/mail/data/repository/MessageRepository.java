package org.jbserv.mail.data.repository;

import java.util.List;

import org.jbserv.mail.data.model.Mailbox;
import org.jbserv.mail.data.model.Message;

public interface MessageRepository {

	Message create(Message message);

	Message create(Mailbox mailbox, String from, byte[] bodyText);

	List<Message> getAll();

	List<Message> getMessagesForMailbox(Mailbox mailbox);

	List<Message> getMessagesForAccount(Mailbox mailbox);

	Message getMessage(Mailbox mailbox, long uid);

	Message update(Message message);

	void delete(Message message);

}
