package org.jbserv.mail.data.repository;

import java.util.List;

import org.jbserv.mail.data.model.Account;
import org.jbserv.mail.data.model.Mailbox;

/**
 * Repository for the mailboxes.
 * 
 * @author Marcel Schwennig
 * @version 1.0
 * 
 */
public interface MailboxRepository {

	/**
	 * Create new mailbox.
	 * 
	 * @param mailbox
	 *            the mailbox to be created
	 * @return returns the created mailbox
	 * @throws RepositoryException
	 */
	Mailbox create(Mailbox mailbox) throws RepositoryException;

	/**
	 * Create new mailbox.
	 * 
	 * @param account
	 * @param name
	 * @return returns the created mailbox
	 * @throws RepositoryException
	 */
	Mailbox create(Account account, String name) throws RepositoryException;

	/**
	 * Retrieve all available mailboxes.
	 * 
	 * @return returns empty list if no mailboxes exist
	 * @throws RepositoryException
	 */
	List<Mailbox> getAll() throws RepositoryException;

	/**
	 * Retrieve all mailboxes for given account.
	 * 
	 * @param account
	 *            the account of the mailboxes
	 * @return returns empty list if no mailboses with given account exist
	 * @throws RepositoryException
	 */
	List<Mailbox> getMailboxesForAccount(Account account)
			throws RepositoryException;

	/**
	 * Retrieve the mailbox with given uid-validity.
	 * 
	 * @param uidValidity
	 *            the uid-validity of the mailbox
	 * @return returns null if no mailbox with given uid-validity exists
	 * @throws RepositoryException
	 */
	Mailbox getMailboxByUIDValidity(long uidValidity)
			throws RepositoryException;

	/**
	 * Retrieve the mailbox for given account and name.
	 * 
	 * @param account
	 *            the account of the mailbox
	 * @param name
	 *            the name of the mailbox
	 * @return returns null if no mailbox with given account and name exists
	 * @throws RepositoryException
	 */
	Mailbox getMailboxByAccountAndName(Account account, String name)
			throws RepositoryException;

	/**
	 * Update mailbox.
	 * 
	 * @param mailbox
	 *            the mailbox to be updated
	 * @return returns the updated mailbox
	 * @throws RepositoryException
	 */
	Mailbox update(Mailbox mailbox) throws RepositoryException;

	/**
	 * Delete mailbox.
	 * 
	 * @param mailbox
	 *            the mailbox to be deleted
	 * @throws RepositoryException
	 */
	void delete(Mailbox mailbox) throws RepositoryException;

}
