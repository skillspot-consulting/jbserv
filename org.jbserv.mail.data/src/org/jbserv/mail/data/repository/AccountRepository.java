package org.jbserv.mail.data.repository;

import java.util.List;

import org.jbserv.mail.data.model.Account;

public interface AccountRepository {

	Account create(Account account);

	Account create(String username, String password);

	List<Account> getAll();

	Account getByUsername(String username);

	Account getByUsernameAndPassword(String username, String password);

	Account update(Account account);

	void delete(Account account);

}
