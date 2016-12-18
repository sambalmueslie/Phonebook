package de.sambalmueslie.phonebook.service.common;

import java.util.LinkedList;
import java.util.List;

import de.sambalmueslie.phonebook.service.config_mgt.ConfigMgr;
import de.sambalmueslie.phonebook.service.config_mgt.PhonebookConfiguration;
import de.sambalmueslie.phonebook.service.rest.RestServiceMgr;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class ManagerProvider {

	public ManagerProvider() {
		manager.add(new ConfigMgr());
		manager.add(new RestServiceMgr());
	}

	public void setup(Bootstrap<PhonebookConfiguration> bootstrap) {
		manager.forEach(m -> m.setup(this, bootstrap));
	}

	public void start(PhonebookConfiguration configuration, Environment environment) {
		manager.forEach(m -> m.start(this, configuration, environment));
	}

	public void teardown() {
		manager.forEach(m -> m.teardown(this));
	}

	private final List<Manager> manager = new LinkedList<>();
}
