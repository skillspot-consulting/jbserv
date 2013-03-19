package org.jbserv.mail.imap.exception;

public class CommandException extends Exception {

	private static final long serialVersionUID = 3855170196049680248L;

	public CommandException() {
		super();
	}

	public CommandException(String msg) {
		super(msg);
	}

	public CommandException(Throwable th) {
		super(th);
	}

	public CommandException(String msg, Throwable th) {
		super(msg, th);
	}

}
