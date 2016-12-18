package de.sambalmueslie.phonebook.rest;

public class PhonebookInfo {
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

	private final long id;
	private final PhonebookLevel level;
	private final String name;
}