package de.sambalmueslie.phonebook.rest.request;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CreateEntryRequest {

	public CreateEntryRequest() {
		// used for JSON
	}

	/**
	 * Constructor.
	 *
	 * @param phonebookId
	 *            {@link #phonebookId}
	 * @param name
	 *            {@link #name}
	 */
	public CreateEntryRequest(long phonebookId, String name) {
		this.phonebookId = phonebookId;
		this.name = name;
	}

	public Map<String, String> getAttributes() {
		return Collections.unmodifiableMap(attributes);
	}

	public String getName() {
		return name;
	}

	public long getPhonebookId() {
		return phonebookId;
	}

	public void set(String name, String value) {
		attributes.put(name, value);
	}

	private final Map<String, String> attributes = new HashMap<>();
	private String name;

	private long phonebookId;

}
