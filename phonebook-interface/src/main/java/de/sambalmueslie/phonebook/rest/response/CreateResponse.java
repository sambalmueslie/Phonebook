package de.sambalmueslie.phonebook.rest.response;

public class CreateResponse {

	public CreateResponse() {
		// used for JSON
	}

	/**
	 * Constructor.
	 *
	 * @param id
	 */
	public CreateResponse(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	private Long id = null;
}
