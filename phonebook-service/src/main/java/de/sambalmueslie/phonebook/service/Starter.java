package de.sambalmueslie.phonebook.service;

import java.util.Arrays;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Starter for the service.
 */
public final class Starter {

	private static Logger logger = LogManager.getLogger(Starter.class);

	/**
	 * Main.
	 *
	 * @param args
	 *            the args
	 * @throws Exception
	 *             on error
	 */
	public static void main(String[] args) throws Exception {
		new Starter("server", "config.yml");
	}

	public Starter(String... args) throws Exception {
		logger.info("Start phonebook application with " + Arrays.toString(args));
		final PhonebookApplication application = new PhonebookApplication();
		application.run(args);
	}
}
