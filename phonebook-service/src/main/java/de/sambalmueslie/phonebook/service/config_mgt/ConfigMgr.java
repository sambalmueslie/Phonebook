package de.sambalmueslie.phonebook.service.config_mgt;

import de.sambalmueslie.phonebook.service.common.Manager;
import de.sambalmueslie.phonebook.service.common.ManagerProvider;
import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class ConfigMgr implements Manager {

	@Override
	public void setup(ManagerProvider provider, Bootstrap<PhonebookConfiguration> bootstrap) {
		bootstrap.setConfigurationSourceProvider(new ResourceConfigurationSourceProvider());
	}

	@Override
	public void start(ManagerProvider provider, PhonebookConfiguration configuration, Environment environment) {
		// TODO Auto-generated method stub

	}

	@Override
	public void teardown(ManagerProvider provider) {
		// TODO Auto-generated method stub

	}

}
