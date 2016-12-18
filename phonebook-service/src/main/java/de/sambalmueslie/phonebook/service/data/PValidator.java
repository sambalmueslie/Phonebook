package de.sambalmueslie.phonebook.service.data;

import javax.persistence.*;

@Entity
@Table(name = "validator")
public class PValidator {
	@Column(nullable = false, length = 50)
	private String expression;
	@Id
	@GeneratedValue
	private long id;
}
