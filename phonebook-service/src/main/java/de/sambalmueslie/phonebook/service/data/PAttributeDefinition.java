package de.sambalmueslie.phonebook.service.data;

import javax.persistence.*;

@Entity
@Table(name = "attribute_definition")
public class PAttributeDefinition {
	public PAttributeDefinition() {
		// used for JPA
	}

	/**
	 * Constructor.
	 *
	 * @param name
	 * @param validator
	 */
	public PAttributeDefinition(String name, PValidator validator) {
		this.name = name;
		this.validator = validator;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public PValidator getValidator() {
		return validator;
	}

	@Id
	@GeneratedValue
	private long id;
	@Column(nullable = false, length = 50, unique = true)
	private String name;
	@ManyToOne
	@JoinColumn(name = "validator", nullable = true)
	private PValidator validator;
}
