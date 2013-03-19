package org.jbserv.mail.persistence.transformer;

import org.jbserv.mail.data.model.Account;
import org.jbserv.mail.persistence.entity.AccountEntity;
import org.jbserv.mail.persistence.model.AccountImpl;

public final class AccountTransformer {

	private AccountTransformer() {

	}

	public static Account transform(AccountEntity entity) {
		if (entity == null) {
			return null;
		}

		final String username = entity.getUsername();
		final String password = entity.getPassword();

		final Account account = new AccountImpl(username, password);

		return account;
	}

}
