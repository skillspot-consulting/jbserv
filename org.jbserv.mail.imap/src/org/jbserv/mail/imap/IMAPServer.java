package org.jbserv.mail.imap;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.jbserv.mail.data.repository.AccountRepository;
import org.jbserv.mail.data.repository.MailboxRepository;
import org.jbserv.mail.data.repository.MessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IMAPServer {

	private static final Logger LOG = LoggerFactory.getLogger(IMAPServer.class);

	public static final int IMAP_PORT = 534;

	private final ExecutorService executorService = Executors
			.newCachedThreadPool();

	private AccountRepository accountRepository;
	private MailboxRepository mailboxRepository;
	private MessageRepository messageRepository;

	/**
	 * Default constructor.
	 */
	public IMAPServer() {

	}

	/**
	 * Init-method for blueprint. Starts the server-socket.
	 */
	public void startup() {
		try (ServerSocket serverSocket = new ServerSocket(IMAP_PORT);) {
			while (true) {
				Socket clientSocket = serverSocket.accept();
				executorService
						.execute(new ClientConnectionImpl(clientSocket,
								accountRepository, mailboxRepository,
								messageRepository));
			}
		} catch (IOException e) {
			LOG.error("cannot create server socket for imap-server", e);
		} finally {
			executorService.shutdown();
		}
	}

	/**
	 * Setter method for blueprint injection.
	 * 
	 * @param accountRepository
	 */
	public void setAccountRepository(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	/**
	 * Setter method for blueprint injection.
	 * 
	 * @param mailboxRepository
	 */
	public void setMailboxRepository(MailboxRepository mailboxRepository) {
		this.mailboxRepository = mailboxRepository;
	}

	/**
	 * Setter method for blueprint injection of property messageRepository.
	 * 
	 * @param messageRepository
	 */
	public void setMessageRepository(MessageRepository messageRepository) {
		this.messageRepository = messageRepository;
	}

}
