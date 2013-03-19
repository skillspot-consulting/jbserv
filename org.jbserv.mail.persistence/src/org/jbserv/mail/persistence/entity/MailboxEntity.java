package org.jbserv.mail.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "T_MAIL_MAILBOX")
public class MailboxEntity extends BaseEntity {

	private static final long serialVersionUID = 5877802815373977259L;

	@Id
	@GeneratedValue
	@Column(name = "UID_VALIDITY")
	private long uidValidity;

	@ManyToOne
	private AccountEntity account;

	@Column(name = "UID_NEXT", nullable = false)
	private long uidNext;

	@Column(name = "NAME", nullable = false)
	private String name;

	/**
	 * Default constructor.
	 */
	public MailboxEntity() {

	}

	/**
	 * Constructor for DTO transformer.
	 * 
	 * @param uidValidity
	 * @param account
	 * @param uidNext
	 * @param name
	 */
	public MailboxEntity(long uidValidity, AccountEntity account, long uidNext,
			String name) {
		this.uidValidity = uidValidity;
		this.account = account;
		this.uidNext = uidNext;
		this.name = name;
	}

	public long getUidValidity() {
		return uidValidity;
	}

	public void setUidValidity(long uidValidity) {
		this.uidValidity = uidValidity;
	}

	public AccountEntity getAccount() {
		return account;
	}

	public void setAccount(AccountEntity account) {
		this.account = account;
	}

	public long getUidNext() {
		return uidNext;
	}

	public void setUidNext(long uidNext) {
		this.uidNext = uidNext;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
