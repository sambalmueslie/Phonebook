package de.sambalmueslie.phonebook.service.rest;

import java.util.LinkedList;
import java.util.List;

import de.sambalmueslie.phonebook.service.common.ManagerProvider;
import de.sambalmueslie.phonebook.service.config_mgt.PhonebookConfiguration;
import de.sambalmueslie.phonebook.service.common.Manager;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class RestServiceMgr implements Manager {

	/**
	 * @return {@link #services}
	 */
	public List<RestService> getAll() {
		return services;
	}

	@Override
	public void setup(ManagerProvider provider, Bootstrap<PhonebookConfiguration> bootstrap) {
		services.add(new AdminService());
		services.add(new SearchService());
	}

	@Override
	public void start(ManagerProvider provider, PhonebookConfiguration configuration, Environment environment) {
		services.forEach(environment.jersey()::register);
	}

	@Override
	public void teardown(ManagerProvider provider) {
		// TODO Auto-generated method stub

	}

	private final List<RestService> services = new LinkedList<>();
}
