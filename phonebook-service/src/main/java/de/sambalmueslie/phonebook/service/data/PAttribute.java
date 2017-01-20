package de.sambalmueslie.phonebook.service.data;

import javax.persistence.*;

@Entity
@Table(name = "attribute")
public class PAttribute {

	public PAttribute() {
		// used for JPA
	}

	/**
	 * Constructor.
	 *
	 * @param definition
	 *            {@link #definition}
	 * @param value
	 *            {@link #value}
	 */
	public PAttribute(PAttributeDefinition definition, String value) {
		this.definition = definition;
		this.value = value;
	}

	public PAttributeDefinition getDefinition() {
		return definition;
	}

	public long getId() {
		return id;
	}

	public String getValue() {
		return value;
	}

	public void setDefinition(PAttributeDefinition definition) {
		this.definition = definition;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@ManyToOne
	@JoinColumn(name = "definition", nullable = false)
	private PAttributeDefinition definition;

	@Id
	@GeneratedValue
	private long id;

	@Column(nullable = false)
	private String value;
}
