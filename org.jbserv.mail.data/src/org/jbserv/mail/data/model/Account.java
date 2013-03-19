package org.jbserv.mail.data.model;

/**
 * Represents the email-account with the login information of the user.
 * 
 * @author Marcel Schwennig
 * @version 1.0
 * 
 */
public interface Account {

	/**
	 * Get the username of the account.
	 * 
	 * @return the username of the account
	 */
	String getUsername();

	/**
	 * Set the username of the account.
	 * 
	 * @param username
	 *            the username of the account
	 */
	void setUsername(String username);

	/**
	 * Get the password of the account.
	 * 
	 * @return the passwor of the account
	 */
	String getPassword();

	/**
	 * Set the password of the account.
	 * 
	 * @param password
	 *            the password of the account
	 */
	void setPassword(String password);

}
