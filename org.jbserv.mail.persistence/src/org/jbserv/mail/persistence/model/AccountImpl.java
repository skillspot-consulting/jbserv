package org.jbserv.mail.persistence.model;

import org.jbserv.mail.data.model.Account;

public class AccountImpl implements Account {

	private final String username;
	private String password;

	public AccountImpl(final String username, final String password) {
		this.username = username;
		this.password = password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public void setPassword(String password) {
		this.password = password;
	}

}
