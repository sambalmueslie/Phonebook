package de.sambalmueslie.phonebook.service.data;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "entry")
public class PEntry {

	/**
	 * Constructor.
	 */
	public PEntry() {
		// used for JPA
	}

	/**
	 * Constructor.
	 * 
	 * @param name
	 * @param attributes
	 */
	public PEntry(String name, List<PAttribute> attributes) {
		this.name = name;
		this.attributes = attributes;
	}

	public List<PAttribute> getAttributes() {
		return attributes;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setAttributes(List<PAttribute> attributes) {
		this.attributes = attributes;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
	private List<PAttribute> attributes;

	@Id
	@GeneratedValue
	private long id;

	@Column(nullable = false)
	private String name;
}
