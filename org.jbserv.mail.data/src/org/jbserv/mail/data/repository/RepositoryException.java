package org.jbserv.mail.data.repository;

public class RepositoryException extends Exception {

	private static final long serialVersionUID = 2332552541664124673L;

	public RepositoryException(String message) {
		super(message);
	}

	public RepositoryException(String message, Throwable th) {
		super(message, th);
	}

}
