package org.jbserv.mail.imap.command;

import org.jbserv.mail.imap.ClientConnection;
import org.jbserv.mail.imap.exception.CommandException;

public class UIDCommand implements Command {

	private final ClientConnection clientConnection;

	protected UIDCommand(final ClientConnection clientConnection) {
		this.clientConnection = clientConnection;
	}

	@Override
	public void execute(String tag, String... args) throws CommandException {
		// TODO Implement me
		throw new CommandException("Not implemented yet");
	}

}
