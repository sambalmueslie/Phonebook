package de.sambalmueslie.phonebook.rest.request;

public class CreateAttributeDefinitionRequest {
	public CreateAttributeDefinitionRequest() {
		// used for JSON
	}

	/**
	 * Constructor.
	 *
	 * @param name
	 *            {@link #name}
	 */
	public CreateAttributeDefinitionRequest(String name) {
		this(name, null);
	}

	/**
	 * Constructor.
	 *
	 * @param name
	 *            {@link #name}
	 * @param validatorId
	 *            {@link #validatorId}
	 */
	public CreateAttributeDefinitionRequest(String name, Long validatorId) {
		this.name = name;
		this.validatorId = validatorId;
	}

	public String getName() {
		return name;
	}

	public Long getValidatorId() {
		return validatorId;
	}

	private String name;

	private Long validatorId;
}
