package org.jbserv.mail.imap.command;

import org.jbserv.mail.data.model.Account;
import org.jbserv.mail.imap.ClientConnection;
import org.jbserv.mail.imap.State;
import org.jbserv.mail.imap.exception.CommandException;

public class LoginCommand implements Command {

	private final ClientConnection clientConnection;

	protected LoginCommand(final ClientConnection clientConnection) {
		this.clientConnection = clientConnection;
	}

	@Override
	public void execute(String tag, String... args) throws CommandException {
		if (args.length != 2) {
			throw new CommandException("BAD invalid argument count");
		}

		String username = args[0];
		String password = args[1];

		Account account = clientConnection.getAccountRepository()
				.getByUsernameAndPassword(username, password);
		if (account != null) {
			clientConnection.setCurrentAccount(account);
			clientConnection.setState(State.AUTHENTICATED);
			clientConnection.writeLine(tag + " OK LOGIN completed");
		} else {
			clientConnection
					.writeLine("NO login failure: username or password rejected");
		}
	}

}
