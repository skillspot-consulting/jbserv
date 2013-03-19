package org.jbserv.mail.persistence.model;

import org.jbserv.mail.data.model.Account;
import org.jbserv.mail.data.model.Mailbox;

public class MailboxImpl implements Mailbox {

	private final Account account;
	private final long uidValidity;
	private final long uidNext;
	private String name;

	public MailboxImpl(final Account account, final long uidValidity,
			final long uidNext, final String name) {
		this.account = account;
		this.uidValidity = uidValidity;
		this.uidNext = uidNext;
		this.name = name;
	}

	@Override
	public Account getAccount() {
		return account;
	}

	@Override
	public long getUIDValidity() {
		return uidValidity;
	}

	@Override
	public long getUIDNext() {
		return uidNext;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

}
