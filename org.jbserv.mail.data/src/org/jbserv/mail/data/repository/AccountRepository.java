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
	 * @return returns the created account or null on failure
	 */
	Account create(Account account);

	/**
	 * Create a new account with given username and password
	 * 
	 * @param username
	 *            the username of the account
	 * @param password
	 *            the password of the account
	 * @return returns the created account or null on failure
	 */
	Account create(String username, String password);

	/**
	 * Retrieve all available accounts.
	 * 
	 * @return all accounts
	 */
	List<Account> getAll();

	/**
	 * Retrieve account by username.
	 * 
	 * @param username
	 *            the username of the account
	 * @return returns null if no account with given username exists
	 */
	Account getByUsername(String username);

	/**
	 * Retrieve account by username and password.
	 * 
	 * @param username
	 *            the username of the account
	 * @param password
	 *            the password of the account
	 * @return returns null of no account with given username and password
	 *         exists
	 */
	Account getByUsernameAndPassword(String username, String password);

	/**
	 * Update the account.
	 * 
	 * @param account
	 *            the account to be updated
	 * @return returns the updated account or null on failure
	 */
	Account update(Account account);

	/**
	 * Delete the account.
	 * 
	 * @param account
	 *            the account to be deleted
	 */
	void delete(Account account);

}
