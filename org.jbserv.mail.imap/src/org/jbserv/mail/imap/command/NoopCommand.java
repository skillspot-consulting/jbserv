package org.jbserv.mail.imap.command;

import org.jbserv.mail.imap.ClientConnection;
import org.jbserv.mail.imap.exception.CommandException;

public class NoopCommand implements Command {

	private final ClientConnection clientConnection;

	protected NoopCommand(final ClientConnection clientConnection) {
		this.clientConnection = clientConnection;
	}

	@Override
	public void execute(String tag, String... args) throws CommandException {
		clientConnection.writeLine(tag + " OK NOOP completed");
	}

}
