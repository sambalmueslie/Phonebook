package de.sambalmueslie.phonebook.service.data;

import javax.persistence.*;

@Entity
@Table(name = "attribute_definition")
public class PAttributeDefinition {
	@Id
	@GeneratedValue
	private long id;
	@Column(nullable = false, length = 50, unique = true)
	private String name;
	@ManyToOne
	@JoinColumn(name = "validator", nullable = false)
	private PValidator validator;
}
