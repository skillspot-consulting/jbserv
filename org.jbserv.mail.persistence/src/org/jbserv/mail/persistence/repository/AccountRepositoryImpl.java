package org.jbserv.mail.persistence.repository;

import static org.jbserv.mail.persistence.InitSessionFactory.currentSession;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Transaction;
import org.jbserv.mail.data.model.Account;
import org.jbserv.mail.data.repository.AccountRepository;
import org.jbserv.mail.persistence.entity.AccountEntity;
import org.jbserv.mail.persistence.transformer.AccountTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AccountRepositoryImpl implements AccountRepository {

	private static final Logger LOG = LoggerFactory
			.getLogger(AccountRepositoryImpl.class);

	@Override
	public Account create(String username, String password) {
		AccountEntity entity = null;
		Transaction tx = null;
		try {
			tx = currentSession().beginTransaction();
			entity = new AccountEntity(username, password);
			currentSession().persist(entity);
			tx.commit();
		} catch (RuntimeException e1) {
			if (tx != null) {
				try {
					tx.rollback();
				} catch (RuntimeException e2) {
					LOG.error("error rolling back transaction", e2);
				}
			}
			throw e1;
		}

		return AccountTransformer.transform(entity);
	}

	@Override
	public Account create(Account account) {
		AccountEntity entity = null;
		Transaction tx = null;
		try {
			tx = currentSession().getTransaction();
			String username = account.getUsername();
			String password = account.getPassword();
			entity = new AccountEntity(username, password);
			currentSession().persist(entity);
			tx.commit();
		} catch (RuntimeException e1) {
			if (tx != null) {
				try {
					tx.rollback();
				} catch (RuntimeException e2) {
					LOG.error("error rolling back transaction", e2);
				}
			}
			throw e1;
		}

		return AccountTransformer.transform(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Account> getAll() {
		List<AccountEntity> result = null;
		List<Account> accounts = new ArrayList<>();
		Transaction tx = null;
		try {
			tx = currentSession().beginTransaction();
			result = currentSession().getNamedQuery(AccountEntity.Q_FIND_ALL)
					.list();

			for (AccountEntity entity : result) {
				Account account = AccountTransformer.transform(entity);
				accounts.add(account);
			}
			tx.commit();
		} catch (RuntimeException e1) {
			if (tx != null) {
				try {
					tx.rollback();
				} catch (RuntimeException e2) {
					LOG.error("error rolling back transaction", e2);
				}
			}
			throw e1;
		}

		return accounts;
	}

	@Override
	public Account getByUsername(String username) {
		AccountEntity entity = null;
		Transaction tx = null;
		try {
			tx = currentSession().beginTransaction();
			entity = (AccountEntity) currentSession()
					.getNamedQuery(AccountEntity.Q_FIND_BY_USERNAME)
					.setParameter("username", username).uniqueResult();
			tx.commit();
		} catch (RuntimeException e1) {
			if (tx != null) {
				try {
					tx.rollback();
				} catch (RuntimeException e2) {
					LOG.error("error rolling back transaction", e2);
				}
			}
			throw e1;
		}

		return AccountTransformer.transform(entity);
	}

	@Override
	public Account getByUsernameAndPassword(String username, String password) {
		AccountEntity entity = null;
		Transaction tx = null;
		try {
			tx = currentSession().beginTransaction();
			entity = (AccountEntity) currentSession()
					.getNamedQuery(
							AccountEntity.Q_FIND_BY_USERNAME_AND_PASSWORD)
					.setParameter("username", username)
					.setParameter("password", password).uniqueResult();
			tx.commit();
		} catch (RuntimeException e1) {
			if (tx != null) {
				try {
					tx.rollback();
				} catch (RuntimeException e2) {
					LOG.error("error rolling back transaction", e2);
				}
			}
			throw e1;
		}

		return AccountTransformer.transform(entity);
	}

	@Override
	public Account update(Account account) {
		AccountEntity entity = null;
		Transaction tx = null;
		try {
			tx = currentSession().beginTransaction();
			entity = (AccountEntity) currentSession()
					.getNamedQuery(AccountEntity.Q_FIND_BY_USERNAME)
					.setParameter("username", account.getUsername())
					.uniqueResult();
			if (entity != null) {
				entity.setPassword(account.getPassword());
			}
			tx.commit();
		} catch (RuntimeException e1) {
			if (tx != null) {
				try {
					tx.rollback();
				} catch (RuntimeException e2) {
					LOG.error("error rolling back transaction", e2);
				}
			}
			throw e1;
		}

		return AccountTransformer.transform(entity);
	}

	@Override
	public void delete(Account account) {
		Transaction tx = null;
		try {
			tx = currentSession().beginTransaction();
			AccountEntity entity = (AccountEntity) currentSession()
					.getNamedQuery(AccountEntity.Q_FIND_BY_USERNAME)
					.setParameter("username", account.getUsername())
					.uniqueResult();
			if (entity != null) {
				currentSession().delete(entity);

			}
			tx.commit();
		} catch (RuntimeException e1) {
			if (tx != null) {
				try {
					tx.rollback();
				} catch (RuntimeException e2) {
					LOG.error("error rolling back transaction", e2);
				}
			}
			throw e1;
		}
	}

}
