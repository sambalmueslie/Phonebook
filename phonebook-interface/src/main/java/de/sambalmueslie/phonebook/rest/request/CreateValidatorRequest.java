package de.sambalmueslie.phonebook.rest.request;

public class CreateValidatorRequest {
	public CreateValidatorRequest() {
		// used for JSON
	}

	/**
	 * Constructor.
	 *
	 * @param expression
	 */
	public CreateValidatorRequest(String expression) {
		this.expression = expression;
	}

	public String getExpression() {
		return expression;
	}

	private String expression;
}
