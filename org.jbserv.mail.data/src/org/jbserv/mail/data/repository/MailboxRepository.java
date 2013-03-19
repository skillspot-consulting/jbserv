package org.jbserv.mail.data.repository;

import java.util.List;

import org.jbserv.mail.data.model.Account;
import org.jbserv.mail.data.model.Mailbox;

public interface MailboxRepository {

	Mailbox create(Mailbox mailbox);

	Mailbox create(Account account, String name);

	List<Mailbox> getAll();

	List<Mailbox> getMailboxesForAccount(Account account);

	Mailbox getMailboxByUIDValidity(long uidValidity);

	Mailbox getMailboxByAccountAndName(Account account, String name);

	Mailbox update(Mailbox mailbox);

	void delete(Mailbox mailbox);

}
