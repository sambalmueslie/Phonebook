package de.sambalmueslie.phonebook.rest;

public class PhonebookInfo {
	public PhonebookInfo() {
		// used for JSON serialisation
	}

	public PhonebookInfo(long id, String name, PhonebookLevel level) {
		this.id = id;
		this.name = name;
		this.level = level;
	}

	public long getId() {
		return id;
	}

	public PhonebookLevel getLevel() {
		return level;
	}

	public String getName() {
		return name;
	}

	private long id;
	private PhonebookLevel level;
	private String name;
}