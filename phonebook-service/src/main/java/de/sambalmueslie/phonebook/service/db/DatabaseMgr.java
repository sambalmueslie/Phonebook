package de.sambalmueslie.phonebook.service.db;

import org.hibernate.SessionFactory;

import de.sambalmueslie.phonebook.service.common.Manager;
import de.sambalmueslie.phonebook.service.common.ManagerProvider;
import de.sambalmueslie.phonebook.service.config_mgt.PhonebookConfiguration;
import de.sambalmueslie.phonebook.service.data.*;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class DatabaseMgr implements Manager {

	public DAOProvider getDaoProvider() {
		return daoProvider;
	}

	public SessionFactory getSessionFactory() {
		return hibernate == null ? null : hibernate.getSessionFactory();
	}

	@Override
	public void setup(ManagerProvider provider, Bootstrap<PhonebookConfiguration> bootstrap) {
		setupHibernateBundle();
		bootstrap.addBundle(hibernate);
	}

	@Override
	public void start(ManagerProvider provider, PhonebookConfiguration configuration, Environment environment) {
		final SessionFactory sessionFactory = hibernate.getSessionFactory();
		daoProvider = new DAOProvider(sessionFactory);

		environment.healthChecks().register("dbconnection", new DbHealthCheck(hibernate));
	}

	@Override
	public void teardown(ManagerProvider provider) {
		// TODO Auto-generated method stub

	}

	private void setupHibernateBundle() {
		hibernate = new HibernateBundle<PhonebookConfiguration>(PAttribute.class, PAttributeDefinition.class, PEntry.class, Phonebook.class, PValidator.class) {
			@Override
			public PooledDataSourceFactory getDataSourceFactory(PhonebookConfiguration config) {
				return config.getDataSourceFactory();
			}
		};

	}

	private DAOProvider daoProvider;
	private HibernateBundle<PhonebookConfiguration> hibernate;
}
