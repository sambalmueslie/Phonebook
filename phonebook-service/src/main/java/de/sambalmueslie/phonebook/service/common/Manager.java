package de.sambalmueslie.phonebook.service.common;

import de.sambalmueslie.phonebook.service.config_mgt.PhonebookConfiguration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public interface Manager {
	void setup(ManagerProvider provider, Bootstrap<PhonebookConfiguration> bootstrap);

	void start(ManagerProvider provider, PhonebookConfiguration configuration, Environment environment);

	void teardown(ManagerProvider provider);
}
