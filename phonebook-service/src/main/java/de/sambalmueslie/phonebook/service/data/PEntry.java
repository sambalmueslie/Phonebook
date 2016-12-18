package de.sambalmueslie.phonebook.service.data;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "entry", schema = "phonebook")
public class PEntry {

	@OneToMany(mappedBy = "attributes", cascade = CascadeType.ALL)
	private List<PAttribute> attributes;

	@Id
	@GeneratedValue
	private long id;

	@Column(nullable = false)
	private String name;
}
