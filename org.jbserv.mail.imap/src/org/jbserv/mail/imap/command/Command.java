package org.jbserv.mail.imap.command;

import org.jbserv.mail.imap.exception.CommandException;

public interface Command {

	void execute(String tag, String... args) throws CommandException;

}
