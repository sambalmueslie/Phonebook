package de.sambalmueslie.phonebook.service.db;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;

import com.codahale.metrics.health.HealthCheck;

import de.sambalmueslie.phonebook.service.config_mgt.PhonebookConfiguration;
import io.dropwizard.hibernate.HibernateBundle;

public class DbHealthCheck extends HealthCheck {

	private static Logger logger = LogManager.getLogger(DbHealthCheck.class);

	public DbHealthCheck(HibernateBundle<PhonebookConfiguration> bundle) {
		this.bundle = bundle;
	}

	@Override
	protected Result check() throws Exception {
		logger.info("Check db connection");
		final Session session = bundle.getSessionFactory().getCurrentSession();
		if (session == null || !session.isConnected()) return Result.unhealthy("No session connected");
		return Result.healthy();
	}

	private final HibernateBundle<PhonebookConfiguration> bundle;

}
