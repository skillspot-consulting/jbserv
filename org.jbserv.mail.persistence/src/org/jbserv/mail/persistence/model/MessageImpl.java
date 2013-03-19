package org.jbserv.mail.persistence.model;

import org.jbserv.mail.data.model.Mailbox;
import org.jbserv.mail.data.model.Message;

public class MessageImpl implements Message {

	private Mailbox mailbox;
	private final long uid;
	private byte[] bodyText;
	private String from;
	private boolean seen;
	private boolean answered;
	private boolean flagged;
	private boolean deleted;
	private boolean draft;
	private final boolean recent;

	public MessageImpl(final Mailbox mailbox, final long uid,
			final byte[] bodyText, final String from, final boolean seen,
			final boolean answered, final boolean flagged,
			final boolean deleted, final boolean draft, final boolean recent) {
		this.mailbox = mailbox;
		this.uid = uid;
		this.bodyText = bodyText;
		this.from = from;
		this.seen = seen;
		this.answered = answered;
		this.flagged = flagged;
		this.deleted = deleted;
		this.draft = draft;
		this.recent = recent;
	}

	@Override
	public Mailbox getMailbox() {
		return mailbox;
	}

	@Override
	public void setMailbox(Mailbox mailbox) {
		this.mailbox = mailbox;
	}

	@Override
	public long getUID() {
		return uid;
	}

	@Override
	public byte[] getBodyText() {
		return bodyText;
	}

	@Override
	public void setBodyText(byte[] bodyText) {
		this.bodyText = bodyText;
	}

	@Override
	public String getFrom() {
		return from;
	}

	@Override
	public void setFrom(String from) {
		this.from = from;
	}

	@Override
	public boolean isSeen() {
		return seen;
	}

	@Override
	public void setSeen(boolean seen) {
		this.seen = seen;
	}

	@Override
	public boolean isAnswered() {
		return answered;
	}

	@Override
	public void setAnswered(boolean answered) {
		this.answered = answered;
	}

	@Override
	public boolean isFlagged() {
		return flagged;
	}

	@Override
	public void setFlagged(boolean flagged) {
		this.flagged = flagged;
	}

	@Override
	public boolean isDeleted() {
		return deleted;
	}

	@Override
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	@Override
	public boolean isDraft() {
		return draft;
	}

	@Override
	public void setDraft(boolean draft) {
		this.draft = draft;
	}

	@Override
	public boolean isRecent() {
		return recent;
	}

}
