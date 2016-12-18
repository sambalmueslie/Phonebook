package de.sambalmueslie.phonebook.service.data;

import javax.persistence.*;

@Entity
@Table(name = "attribute", schema = "phonebook")
public class PAttribute {
	@ManyToOne
	@JoinColumn(name = "definition", nullable = false)
	private PAttributeDefinition definition;
	@Id
	@GeneratedValue
	private long id;

	@Column(nullable = false)
	private String value;
}
