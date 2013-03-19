package org.jbserv.mail.imap.command;

import org.jbserv.mail.imap.ClientConnection;
import org.jbserv.mail.imap.State;
import org.jbserv.mail.imap.exception.CommandException;

public class LogoutCommand implements Command {

	private final ClientConnection clientConnection;

	protected LogoutCommand(final ClientConnection clientConnection) {
		this.clientConnection = clientConnection;
	}

	@Override
	public void execute(String tag, String... args) throws CommandException {
		clientConnection.writeLine("* BYE IMAP4rev1 Server logging out");
		clientConnection.writeLine(tag + " OK LOGOUT completed");
		clientConnection.setState(State.LOGOUT);
	}

}
