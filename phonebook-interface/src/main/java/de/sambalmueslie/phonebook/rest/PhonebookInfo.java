package de.sambalmueslie.phonebook.rest;

import java.util.Set;

public class PhonebookInfo {
	public PhonebookInfo() {
		// used for JSON serialisation
	}

	public PhonebookInfo(long id, String name, PhonebookLevel level, Set<String> attributes) {
		this.id = id;
		this.name = name;
		this.level = level;
		this.attributes = attributes;
	}

	public Set<String> getAttributes() {
		return attributes;
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

	private Set<String> attributes;
	private long id;
	private PhonebookLevel level;
	private String name;
}