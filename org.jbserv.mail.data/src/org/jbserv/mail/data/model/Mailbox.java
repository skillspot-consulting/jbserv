package org.jbserv.mail.data.model;

/**
 * Represents the mailbox of an email-account. The mailbox must be connected
 * with a valid account. The uid-validity must be a unique id, identifying the
 * mailbox server-wide. The uid-next must be incremented when a new message
 * arrived in the mailbox. The name must be unique in relation to to the
 * connected account.
 * 
 * @author Marcel Schwennig
 * @version 1.0
 * 
 */
public interface Mailbox {

	/**
	 * Get the account of the mailbox.
	 * 
	 * @return the account of the mailbox
	 */
	Account getAccount();

	/**
	 * Set the account of the mailbox.
	 * 
	 * @param account
	 *            the account of the mailbox
	 */
	void setAccount(Account account);

	/**
	 * Get the uid-validity of the mailbox.
	 * 
	 * @return the uid-validity of the mailbox
	 */
	long getUIDValidity();

	/**
	 * Set the uid-validity of the mailbox.
	 * 
	 * @param uidValidity
	 *            the uid-validity of the mailbbox
	 */
	void setUIDValidity(long uidValidity);

	/**
	 * Get the uid-next of the mailbox.
	 * 
	 * @return the uid-next of the mailbox
	 */
	long getUIDNext();

	/**
	 * Set the uid-next of the mailbox.
	 * 
	 * @param uidNext
	 *            the uid-next of the mailbox
	 */
	void setUIDNext(long uidNext);

	/**
	 * Get the name of the mailbox.
	 * 
	 * @return the name of the mailbox
	 */
	String getName();

	/**
	 * Set the name of the mailbox.
	 * 
	 * @param name
	 *            the name of the mailbox
	 */
	void setName(String name);

}
