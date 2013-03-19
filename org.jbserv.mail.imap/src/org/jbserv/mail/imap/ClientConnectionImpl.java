package org.jbserv.mail.imap;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Date;
import java.util.Timer;

import org.jbserv.mail.data.model.Account;
import org.jbserv.mail.data.model.Mailbox;
import org.jbserv.mail.data.repository.AccountRepository;
import org.jbserv.mail.data.repository.MailboxRepository;
import org.jbserv.mail.data.repository.MessageRepository;
import org.jbserv.mail.imap.command.Command;
import org.jbserv.mail.imap.command.CommandFactory;
import org.jbserv.mail.imap.command.CommandType;
import org.jbserv.mail.imap.exception.CommandException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientConnectionImpl implements Runnable, ClientConnection {

	protected static final Logger LOG = LoggerFactory
			.getLogger(ClientConnectionImpl.class);

	private volatile State state = State.NOT_AUTHENTICATED;
	private final Socket clientSocket;
	private final AccountRepository accountRepository;
	private final MailboxRepository mailboxRepository;
	private final MessageRepository messageRepository;
	private final CommandFactory commandFactory;
	private Account currentAccount = null;
	private Mailbox currentMailbox = null;
	private Timer timer = null;

	public ClientConnectionImpl(final Socket clientSocket,
			final AccountRepository accountRepository,
			final MailboxRepository mailboxRepository,
			final MessageRepository messageRepository) {
		this.clientSocket = clientSocket;
		this.accountRepository = accountRepository;
		this.mailboxRepository = mailboxRepository;
		this.messageRepository = messageRepository;
		commandFactory = new CommandFactory(this);
		timer = new Timer();
	}

	@Override
	public void run() {
		long time = new Date().getTime() + (60000 * 3);
		timer.schedule(new ClientConnectionTimerTask(this), time);
		while (!clientSocket.isClosed() && !state.equals(State.LOGOUT)) {
			try {
				String line = readLine();
				CommandType commandType = parseCommandType(line);
				String tag = parseTag(line);
				String[] args = parseCommandArguments(line);
				Command command = commandFactory.getInstance(commandType);
				command.execute(tag, args);
				timer.cancel();
				time = new Date().getTime() + (60000 * 3);
				timer = new Timer();
				timer.schedule(new ClientConnectionTimerTask(this), time);
			} catch (CommandException e) {
				LOG.error("command error in imap-client-connection", e);
				writeLine("BAD " + e.getLocalizedMessage());
			}
		}
		shutdown();
	}

	@Override
	public void writeLine(String line) {
		if (clientSocket == null || clientSocket.isClosed()) {
			throw new IllegalStateException(
					"cannot write to client-socket because it is closed or null");
		}
		try {
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
					clientSocket.getOutputStream()));
			writer.write(line + "\r\n");
			writer.flush();
		} catch (IOException e) {
			LOG.error(
					"Cannot write to client-socket in imap-client-connection",
					e);
		}

	}

	private String[] parseCommandArguments(String line) {
		String[] tokens = line.split(" ");
		int length = tokens.length - 2;
		if (length > 0) {
			String[] args = new String[length];
			for (int i = 0; i < length; i++) {
				args[i] = tokens[i + 2];
			}
			return args;
		} else {
			return new String[0];
		}
	}

	private String parseTag(String line) {
		String[] tokens = line.split(" ");
		return tokens.length > 0 ? tokens[0] : "";
	}

	private CommandType parseCommandType(String line) throws CommandException {
		String[] tokens = line.split(" ");
		if (tokens.length >= 2) {
			String cmd = tokens[1];
			switch (cmd.toUpperCase()) {
			case "APPEND":
				return CommandType.APPEND;
			case "AUTHENTICATE":
				return CommandType.AUTHENTICATE;
			case "CAPABILITY":
				return CommandType.CAPABILITY;
			case "CHECK":
				return CommandType.CHECK;
			case "CLOSE":
				return CommandType.CLOSE;
			case "COPY":
				return CommandType.COPY;
			case "CREATE":
				return CommandType.CREATE;
			case "DELETE":
				return CommandType.DELETE;
			case "EXAMINE":
				return CommandType.EXAMINE;
			case "EXPUNGE":
				return CommandType.EXPUNGE;
			case "FETCH":
				return CommandType.FETCH;
			case "LIST":
				return CommandType.LIST;
			case "LOGIN":
				return CommandType.LOGIN;
			case "LOGOUT":
				return CommandType.LOGOUT;
			case "LSUB":
				return CommandType.LSUB;
			case "NOOP":
				return CommandType.NOOP;
			case "RENAME":
				return CommandType.RENAME;
			case "SEARCH":
				return CommandType.SEARCH;
			case "SELECT":
				return CommandType.SELECT;
			case "STARTTLS":
				return CommandType.STARTTLS;
			case "STATUS":
				return CommandType.STATUS;
			case "STORE":
				return CommandType.STORE;
			case "SUBSCRIBE":
				return CommandType.SUBSCRIBE;
			case "UID":
				return CommandType.UID;
			case "UNSUBSCRIBE":
				return CommandType.UNSUBSCRIBE;
			default:
				throw new CommandException("unkown command");
			}
		} else {
			throw new CommandException("unkown command");
		}
	}

	@Override
	public String readLine() {
		if (clientSocket == null || clientSocket.isClosed()) {
			throw new IllegalStateException(
					"cannot read from client-socket because it is closed or null");
		}
		String line = "";

		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					clientSocket.getInputStream()));
			line = reader.readLine();
		} catch (IOException e) {
			LOG.error(
					"cannot read from client-socket in imap-client-connection",
					e);
			Thread.currentThread().interrupt();
		}

		return line;
	}

	@Override
	public AccountRepository getAccountRepository() {
		return accountRepository;
	}

	@Override
	public MailboxRepository getMailboxRepository() {
		return mailboxRepository;
	}

	@Override
	public MessageRepository getMessageRepository() {
		return messageRepository;
	}

	@Override
	public State getState() {
		return state;
	}

	@Override
	public void setState(State state) {
		this.state = state;
	}

	@Override
	public Account getCurrentAccount() {
		return currentAccount;
	}

	@Override
	public void setCurrentAccount(Account account) {
		this.currentAccount = account;
	}

	@Override
	public Mailbox getCurrentMailbox() {
		return currentMailbox;
	}

	@Override
	public void setCurrentMailbox(Mailbox mailbox) {
		this.currentMailbox = mailbox;
	}

	@Override
	public void shutdown() {
		try {
			clientSocket.close();
		} catch (IOException e) {
			LOG.error("cannot close client-socket", e);
			Thread.currentThread().interrupt();
		}
	}

}
