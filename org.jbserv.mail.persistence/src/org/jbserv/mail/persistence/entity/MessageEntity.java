package org.jbserv.mail.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "T_MAIL_MESSAGE")
public class MessageEntity extends BaseEntity {

	private static final long serialVersionUID = -3067326072858355879L;

	@Id
	@GeneratedValue
	@Column(name = "UID")
	private long uid;

	@ManyToOne
	private MailboxEntity mailbox;

	@Lob
	@Column(name = "BODY_TEXT", nullable = false)
	private byte[] bodyText;

	@Column(name = "MAIL_FROM", nullable = false)
	private String from;

	@Column(name = "SEEN")
	private boolean seen;

	@Column(name = "ASNWERED")
	private boolean answered;

	@Column(name = "FLAGGED")
	private boolean flagged;

	@Column(name = "DELETED")
	private boolean deleted;

	@Column(name = "DRAFT")
	private boolean draft;

	@Column(name = "RECENT")
	private boolean recent;

	public MessageEntity() {

	}

	public MessageEntity(final MailboxEntity mailbox, final long uid,
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

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public MailboxEntity getMailbox() {
		return mailbox;
	}

	public void setMailbox(MailboxEntity mailbox) {
		this.mailbox = mailbox;
	}

	public byte[] getBodyText() {
		return bodyText;
	}

	public void setBodyText(byte[] bodyText) {
		this.bodyText = bodyText;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public boolean isSeen() {
		return seen;
	}

	public void setSeen(boolean seen) {
		this.seen = seen;
	}

	public boolean isAnswered() {
		return answered;
	}

	public void setAnswered(boolean answered) {
		this.answered = answered;
	}

	public boolean isFlagged() {
		return flagged;
	}

	public void setFlagged(boolean flagged) {
		this.flagged = flagged;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public boolean isDraft() {
		return draft;
	}

	public void setDraft(boolean draft) {
		this.draft = draft;
	}

	public boolean isRecent() {
		return recent;
	}

	public void setRecent(boolean recent) {
		this.recent = recent;
	}

}
