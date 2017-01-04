package de.sambalmueslie.phonebook.client.rest;

import javax.ws.rs.client.WebTarget;

abstract class BaseRestClient {

	/**
	 * Constructor.
	 *
	 * @param connector
	 *            {@link #connector}
	 */
	protected BaseRestClient(RestConnector connector) {
		this.connector = connector;
	}

	protected WebTarget getTarget(String path) {
		return connector.getTarget(path);
	}

	private final RestConnector connector;
}
