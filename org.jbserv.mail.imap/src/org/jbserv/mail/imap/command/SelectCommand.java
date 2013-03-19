package org.jbserv.mail.imap.command;

import java.util.List;

import org.jbserv.mail.data.model.Account;
import org.jbserv.mail.data.model.Mailbox;
import org.jbserv.mail.data.model.Message;
import org.jbserv.mail.imap.ClientConnection;
import org.jbserv.mail.imap.State;
import org.jbserv.mail.imap.exception.CommandException;

public class SelectCommand implements Command {

	private final ClientConnection clientConnection;

	protected SelectCommand(final ClientConnection clientConnection) {
		this.clientConnection = clientConnection;
	}

	@Override
	public void execute(String tag, String... args) throws CommandException {
		if (args.length != 1) {
			throw new CommandException("BAD invalid argument count");
		}

		if (clientConnection.getState().equals(State.NOT_AUTHENTICATED)) {
			throw new CommandException("NO can't access mailbox");
		}

		Account account = clientConnection.getCurrentAccount();
		String name = args[0];

		Mailbox mailbox = clientConnection.getMailboxRepository()
				.getMailboxByAccountAndName(account, name);
		if (mailbox != null) {
			List<Message> allMessages = clientConnection.getMessageRepository()
					.getMessagesForMailbox(mailbox);
			clientConnection.writeLine("* " + allMessages.size() + " EXISTS");
			int recentCount = 0;
			int firstUnseen = -1;
			for (int i = 0; i < allMessages.size(); i++) {
				if (allMessages.get(i).isRecent()) {
					recentCount++;
				}
				if (firstUnseen < 0 && !allMessages.get(i).isSeen()) {
					firstUnseen = i;
				}
			}
			clientConnection.writeLine("* " + recentCount + " RECENT");
			clientConnection.writeLine("* OK [UNSEEN" + firstUnseen
					+ "] Message " + firstUnseen + " is first unseen");
			clientConnection.writeLine("* OK [UIDVALIDITY "
					+ mailbox.getUIDValidity() + "] UIDs valid");
			clientConnection.writeLine("* OK [UIDNEXT " + mailbox.getUIDNext()
					+ "] Predicted next UID");
			clientConnection
					.writeLine("* FLAG (\\Answered \\Flagged \\Deleted \\Seen \\Draft)");
			clientConnection
					.writeLine("* OK [PERMANENTFLAGS (\\Deleted \\Seen \\*)] Limited");
			clientConnection.writeLine(tag
					+ " OK [READ-WRITE] SELECT completed");
		} else {
			clientConnection.setState(State.AUTHENTICATED);
			clientConnection.writeLine("NO no in authenticated state");
		}

	}

}
