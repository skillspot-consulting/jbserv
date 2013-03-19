package org.jbserv.mail.data.model;

public interface Message {

	Mailbox getMailbox();

	void setMailbox(Mailbox mailbox);

	long getUID();

	byte[] getBodyText();

	void setBodyText(byte[] bodyText);

	String getFrom();

	void setFrom(String from);

	boolean isSeen();

	void setSeen(boolean seen);

	boolean isAnswered();

	void setAnswered(boolean answered);

	boolean isFlagged();

	void setFlagged(boolean flagged);

	boolean isDeleted();

	void setDeleted(boolean deleted);

	boolean isDraft();

	void setDraft(boolean draft);

	boolean isRecent();

}
