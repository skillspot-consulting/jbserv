package org.jbserv.mail.imap.command;

import org.jbserv.mail.imap.ClientConnection;
import org.jbserv.mail.imap.exception.CommandException;

public class CapabilityCommand implements Command {
	private final ClientConnection clientConnection;

	protected CapabilityCommand(final ClientConnection clientConnection) {
		this.clientConnection = clientConnection;
	}

	@Override
	public void execute(String tag, String... args) throws CommandException {
		StringBuffer sb = new StringBuffer();
		sb.append("* CAPABILITY IMAP4rev1 STARTTLS AUTH=PLAIN\r\n");
		sb.append(tag + " OK CAPABILITY completed");
		clientConnection.writeLine(sb.toString());
	}

}
