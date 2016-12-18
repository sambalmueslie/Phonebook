package de.sambalmueslie.phonebook.service;

/**
 * Starter for the service.
 */
public final class Starter {
	/**
	 * Main.
	 *
	 * @param args
	 *            the args
	 * @throws Exception
	 *             on error
	 */
	public static void main(String[] args) throws Exception {
		new PhonebookApplication().run(args);
	}

	/**
	 * Constructor.
	 */
	private Starter() {
		// empty by decision
	}
}
