package org.jbserv.mail.data.repository;

import java.util.List;

import org.jbserv.mail.data.model.Mailbox;
import org.jbserv.mail.data.model.Message;

/**
 * Repository for messages.
 * 
 * @author Marcel Scwhwennig
 * @version 1.0
 * 
 */
public interface MessageRepository {

	/**
	 * Create new message.
	 * 
	 * @param message
	 *            the message to be created
	 * @return returns the created message
	 * @throws RepositoryException
	 */
	Message create(Message message) throws RepositoryException;

	/**
	 * Create new message.
	 * 
	 * @param mailbox
	 *            the mailbox of the message
	 * @param from
	 *            the sender of the message
	 * @param bodyText
	 *            the body-text of the message
	 * @return returns the created message
	 * @throws RepositoryException
	 */
	Message create(Mailbox mailbox, String from, byte[] bodyText)
			throws RepositoryException;

	/**
	 * Retrieve all message.
	 * 
	 * @return returns an empty list if no message exist
	 * @throws RepositoryException
	 */
	List<Message> getAll() throws RepositoryException;

	/**
	 * Retrieve all message of given mailbox.
	 * 
	 * @param mailbox
	 *            the mailbox of the messages
	 * @return returns an empty list if no message with given mailbox exist
	 * @throws RepositoryException
	 */
	List<Message> getMessagesForMailbox(Mailbox mailbox)
			throws RepositoryException;

	List<Message> getMessagesForAccount(Mailbox mailbox)
			throws RepositoryException;

	/**
	 * Retrieve message with given mailbox and uid.
	 * 
	 * @param mailbox
	 *            the mailbox of the message
	 * @param uid
	 *            the uid of the message
	 * @return returns null if no message with given mailbox and uid exist
	 * @throws RepositoryException
	 */
	Message getMessage(Mailbox mailbox, long uid) throws RepositoryException;

	/**
	 * Update message.
	 * 
	 * @param message
	 *            the message to be updated
	 * @return returns the updated message
	 * @throws RepositoryException
	 */
	Message update(Message message) throws RepositoryException;

	/**
	 * Delete message.
	 * 
	 * @param message
	 *            the message to be deleted
	 * @throws RepositoryException
	 */
	void delete(Message message) throws RepositoryException;

}
