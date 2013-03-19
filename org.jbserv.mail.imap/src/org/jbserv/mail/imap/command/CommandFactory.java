package org.jbserv.mail.imap.command;

import org.jbserv.mail.imap.ClientConnection;

public final class CommandFactory {

	private final ClientConnection clientConnection;

	public CommandFactory(ClientConnection clientConnection) {
		this.clientConnection = clientConnection;
	}

	public Command getInstance(CommandType type) {
		Command command = null;

		switch (type) {
		case CAPABILITY:
			command = new CapabilityCommand(clientConnection);
			break;
		case APPEND:
			command = new AppendCommand(clientConnection);
			break;
		case AUTHENTICATE:
			command = new AuthenticateCommand(clientConnection);
			break;
		case CHECK:
			command = new CheckCommand(clientConnection);
			break;
		case CLOSE:
			command = new CloseCommand(clientConnection);
			break;
		case COPY:
			command = new CopyCommand(clientConnection);
			break;
		case CREATE:
			command = new CreateCommand(clientConnection);
			break;
		case DELETE:
			command = new DeleteCommand(clientConnection);
			break;
		case EXAMINE:
			command = new ExamineCommand(clientConnection);
			break;
		case EXPUNGE:
			command = new ExpungeCommand(clientConnection);
			break;
		case FETCH:
			command = new FetchCommand(clientConnection);
			break;
		case LIST:
			command = new ListCommand(clientConnection);
			break;
		case LOGIN:
			command = new LoginCommand(clientConnection);
			break;
		case LOGOUT:
			command = new LogoutCommand(clientConnection);
			break;
		case LSUB:
			command = new LsubCommand(clientConnection);
			break;
		case NOOP:
			command = new NoopCommand(clientConnection);
			break;
		case RENAME:
			command = new RenameCommand(clientConnection);
			break;
		case SEARCH:
			command = new SearchCommand(clientConnection);
			break;
		case SELECT:
			command = new SelectCommand(clientConnection);
			break;
		case STARTTLS:
			command = new StartTLSCommand(clientConnection);
			break;
		case STATUS:
			command = new StatusCommand(clientConnection);
			break;
		case STORE:
			command = new StoreCommand(clientConnection);
			break;
		case SUBSCRIBE:
			command = new SubscribeCommand(clientConnection);
			break;
		case UID:
			command = new UIDCommand(clientConnection);
			break;
		case UNSUBSCRIBE:
			command = new UnsubscribeCommand(clientConnection);
			break;
		default:
			break;
		}

		return command;
	}

}
