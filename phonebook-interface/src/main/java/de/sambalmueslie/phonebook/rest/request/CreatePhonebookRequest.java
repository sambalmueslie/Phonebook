package de.sambalmueslie.phonebook.rest.request;

import de.sambalmueslie.phonebook.rest.PhonebookLevel;

public class CreatePhonebookRequest {

	public CreatePhonebookRequest() {
		// used for JSON
	}

	/**
	 * Constructor.
	 *
	 * @param name
	 *            {@link #name}
	 */
	public CreatePhonebookRequest(String name) {
		this(name, PhonebookLevel.GLOBAL);
	}

	/**
	 * Constructor.
	 *
	 * @param name
	 *            {@link #name}
	 * @param level
	 *            {@link #level}
	 */
	public CreatePhonebookRequest(String name, PhonebookLevel level) {
		this.level = level;
		this.name = name;
	}

	public PhonebookLevel getLevel() {
		return level;
	}

	public String getName() {
		return name;
	}

	/** the {@link PhonebookLevel}. */
	private PhonebookLevel level;

	/** the phonebook name. */
	private String name;
}
