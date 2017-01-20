package de.sambalmueslie.phonebook.service.data;

import java.util.List;

import javax.persistence.*;

import de.sambalmueslie.phonebook.rest.PhonebookLevel;

@Entity
@Table(name = "phonebook")
public class Phonebook {

	public Phonebook() {
		// used for JPA
	}

	public Phonebook(String name, PhonebookLevel level) {
		this.name = name;
		this.level = level;
	}

	public void addEntry(PEntry entry) {
		entries.add(entry);
	}

	public List<PEntry> getEntries() {
		return entries;
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

	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "phonebook_entries")
	private List<PEntry> entries;
	@Id
	@GeneratedValue
	private long id;
	@Column
	@Enumerated(EnumType.ORDINAL)
	private PhonebookLevel level;
	@Column(nullable = false, unique = true, length = 50)
	private String name;
}
