package org.jbserv.mail.imap;

import org.jbserv.mail.data.model.Account;
import org.jbserv.mail.data.model.Mailbox;
import org.jbserv.mail.data.repository.AccountRepository;
import org.jbserv.mail.data.repository.MailboxRepository;
import org.jbserv.mail.data.repository.MessageRepository;

public interface ClientConnection {

	AccountRepository getAccountRepository();

	MailboxRepository getMailboxRepository();

	MessageRepository getMessageRepository();

	State getState();

	void setState(State state);

	Account getCurrentAccount();

	void setCurrentAccount(Account account);

	Mailbox getCurrentMailbox();

	void setCurrentMailbox(Mailbox mailbox);

	String readLine();

	void writeLine(String line);

	void shutdown();

}
