package org.jbserv.mail.persistence;

import org.jbserv.mail.data.model.Account;
import org.jbserv.mail.data.model.Mailbox;
import org.jbserv.mail.data.repository.AccountRepository;
import org.jbserv.mail.data.repository.MailboxRepository;
import org.jbserv.mail.data.repository.MessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestDataPopulator {

	private static final Logger LOG = LoggerFactory
			.getLogger(TestDataPopulator.class);

	private AccountRepository accountRepository;
	private MailboxRepository mailboxRepository;
	private MessageRepository messageRepository;

	public TestDataPopulator() {

	}

	public void populate() {
		Account account = accountRepository.getByUsername("testuser");
		if (account == null) {
			account = accountRepository.create("testuser", "testpassword");
			Mailbox mailbox = mailboxRepository.create(account, "INBOX");
			messageRepository.create(mailbox, "server-team",
					"Welcome!".getBytes());
			LOG.info("Created new account: " + account);
			LOG.info("Created mailbox INBOX for account: " + account);
			LOG.info("Created welcomde message for account: " + account);

			LOG.info("Created test-account:" + account);
			LOG.info("This message should not appear in production mode");
		}
	}

	public void setAccountRepository(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	public void setMailboxRepository(MailboxRepository mailboxRepository) {
		this.mailboxRepository = mailboxRepository;
	}

	public void setMessageRepository(MessageRepository messageRepository) {
		this.messageRepository = messageRepository;
	}

}
