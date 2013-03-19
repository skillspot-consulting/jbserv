package org.jbserv.mail.data.model;

public interface Mailbox {

	Account getAccount();

	long getUIDValidity();

	long getUIDNext();

	String getName();

	void setName(String name);

}
