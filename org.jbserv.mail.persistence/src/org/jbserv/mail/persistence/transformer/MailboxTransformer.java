package org.jbserv.mail.persistence.transformer;

import org.jbserv.mail.data.model.Account;
import org.jbserv.mail.data.model.Mailbox;
import org.jbserv.mail.persistence.entity.MailboxEntity;
import org.jbserv.mail.persistence.model.MailboxImpl;

public final class MailboxTransformer {

	private MailboxTransformer() {

	}

	public static Mailbox transform(MailboxEntity entity) {
		if (entity == null) {
			return null;
		}

		final Account account = AccountTransformer.transform(entity
				.getAccount());
		final long uidValidity = entity.getUidValidity();
		final long uidNext = entity.getUidNext();
		final String name = entity.getName();

		final Mailbox mailbox = new MailboxImpl(account, uidValidity, uidNext,
				name);

		return mailbox;
	}

}
