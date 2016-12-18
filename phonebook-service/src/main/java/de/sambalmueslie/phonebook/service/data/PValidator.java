package de.sambalmueslie.phonebook.service.data;

import javax.persistence.*;

@Entity
@Table(name = "validator", schema = "phonebook")
public class PValidator {
	@Column(nullable = false, length = 50)
	private String expression;
	@Id
	@GeneratedValue
	private long id;
}
