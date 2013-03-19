package org.jbserv.mail.data.repository;

import java.util.List;

import org.jbserv.mail.data.model.Account;

/**
 * Repository for the email accounts.
 * 
 * @author Marcel Schwennig
 * @version 1.0
 * 
 */
public interface AccountRepository {

	/**
	 * Create a new account.
	 * 
	 * @param account
	 *            the account to be created
	 * @return returns the created account
	 * @throws RepositoryException
	 */
	Account create(Account account) throws RepositoryException;

	/**
	 * Create a new account with given username and password
	 * 
	 * @param username
	 *            the username of the account
	 * @param password
	 *            the password of the account
	 * @return returns the created account
	 * @throws RepositoryException
	 */
	Account create(String username, String password);

	/**
	 * Retrieve all available accounts.
	 * 
	 * @return all accounts
	 * @throws RepositoryException
	 */
	List<Account> getAll() throws RepositoryException;

	/**
	 * Retrieve account by username.
	 * 
	 * @param username
	 *            the username of the account
	 * @return returns null if no account with given username exists
	 * @throws RepositoryException
	 */
	Account getByUsername(String username) throws RepositoryException;

	/**
	 * Retrieve account by username and password.
	 * 
	 * @param username
	 *            the username of the account
	 * @param password
	 *            the password of the account
	 * @return returns null of no account with given username and password
	 *         exists
	 * @throws RepositoryException
	 */
	Account getByUsernameAndPassword(String username, String password)
			throws RepositoryException;

	/**
	 * Update the account.
	 * 
	 * @param account
	 *            the account to be updated
	 * @return returns the updated account or null on failure
	 * @throws RepositoryException
	 */
	Account update(Account account) throws RepositoryException;

	/**
	 * Delete the account.
	 * 
	 * @param account
	 *            the account to be deleted
	 * @throws RepositoryException
	 */
	void delete(Account account) throws RepositoryException;

}
