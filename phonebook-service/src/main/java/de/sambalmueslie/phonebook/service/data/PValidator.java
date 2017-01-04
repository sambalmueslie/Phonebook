package de.sambalmueslie.phonebook.service.data;

import javax.persistence.*;

@Entity
@Table(name = "validator")
public class PValidator {
	public PValidator() {
		// used for JPA
	}

	/**
	 * Constructor.
	 *
	 * @param expression
	 */
	public PValidator(String expression) {
		this.expression = expression;
	}

	public String getExpression() {
		return expression;
	}

	public long getId() {
		return id;
	}

	@Column(nullable = false, length = 50)
	private String expression;
	@Id
	@GeneratedValue
	private long id;
}
