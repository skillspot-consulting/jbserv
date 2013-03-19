package org.jbserv.mail.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_MAIL_ACCOUNT")
public class AccountEntity extends BaseEntity {

	private static final long serialVersionUID = 4000455276153609559L;

	public static final String Q_FIND_ALL = "AccountEntity.findAll";
	public static final String Q_FIND_BY_USERNAME = "AccountEntity.findByUsername";
	public static final String Q_FIND_BY_USERNAME_AND_PASSWORD = "AccountEntity.findByUsernameAndPassword";

	@Id
	@GeneratedValue
	private long id;

	@Column(name = "USERNAME", unique = true, nullable = false)
	private String username;

	@Column(name = "PASSWORD", nullable = false)
	private String password;

	/**
	 * Default constructor
	 */
	public AccountEntity() {

	}

	/**
	 * Constructor for DTO transformer.
	 * 
	 * @param username
	 * @param password
	 */
	public AccountEntity(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
