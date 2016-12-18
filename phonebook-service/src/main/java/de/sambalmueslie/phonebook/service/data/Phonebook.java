package de.sambalmueslie.phonebook.service.data;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "phonebook", schema = "phonebook")
public class Phonebook {
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "phonebook_entries")
	private final List<PEntry> entries = new LinkedList<>();

	@Id
	@GeneratedValue
	private long id;
	@Column(nullable = false, unique = true, length = 50)
	private String name;

}
