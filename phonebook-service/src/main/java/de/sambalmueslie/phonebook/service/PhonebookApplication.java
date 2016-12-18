package de.sambalmueslie.phonebook.service;

import de.sambalmueslie.phonebook.service.rest.HelloWorld;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class PhonebookApplication extends Application<PhonebookConfiguration> {

	@Override
	public String getName() {
		return "phonebook";
	}

	@Override
	public void run(PhonebookConfiguration configuration, Environment environment) throws Exception {
		environment.jersey().register(new HelloWorld());
	}

}
