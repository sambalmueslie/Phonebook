package de.sambalmueslie.phonebook.service;

import de.sambalmueslie.phonebook.service.common.ManagerProvider;
import de.sambalmueslie.phonebook.service.config_mgt.PhonebookConfiguration;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class PhonebookApplication extends Application<PhonebookConfiguration> {

	/**
	 * Constructor.
	 */
	public PhonebookApplication() {
		managerProvider = new ManagerProvider();
	}

	@Override
	public String getName() {
		return "phonebook";
	}

	@Override
	public void initialize(Bootstrap<PhonebookConfiguration> bootstrap) {
		managerProvider.setup(bootstrap);
		super.initialize(bootstrap);
	}

	@Override
	public void run(PhonebookConfiguration configuration, Environment environment) throws Exception {
		managerProvider.start(configuration, environment);
	}

	/** the {@link ManagerProvider} */
	private final ManagerProvider managerProvider;

}
