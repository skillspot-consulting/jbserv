package org.jbserv.mail.persistence.transformer;

import org.jbserv.mail.data.model.Mailbox;
import org.jbserv.mail.data.model.Message;
import org.jbserv.mail.persistence.entity.MessageEntity;
import org.jbserv.mail.persistence.model.MessageImpl;

public final class MessageTransformer {

	private MessageTransformer() {

	}

	public static Message transform(MessageEntity entity) {
		if (entity == null) {
			return null;
		}

		final Mailbox mailbox = MailboxTransformer.transform(entity
				.getMailbox());
		final long uid = entity.getUid();
		final byte[] bodyText = entity.getBodyText();
		final String from = entity.getFrom();
		final boolean seen = entity.isSeen();
		final boolean answered = entity.isAnswered();
		final boolean flagged = entity.isFlagged();
		final boolean deleted = entity.isDeleted();
		final boolean draft = entity.isDraft();
		final boolean recent = entity.isRecent();

		final Message message = new MessageImpl(mailbox, uid, bodyText, from,
				seen, answered, flagged, deleted, draft, recent);

		return message;
	}
}
