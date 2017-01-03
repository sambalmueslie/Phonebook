package de.sambalmueslie.phonebook.client.rest;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

public abstract class BaseRestClient {

	/**
	 * Constructor.
	 *
	 * @param serverUrl
	 *            {@link #serverUrl}
	 */
	protected BaseRestClient(String serverUrl) {
		this.serverUrl = serverUrl;
	}

	protected WebTarget getTarget(String path) {
		final Client client = ClientBuilder.newClient();
		final String uri = serverUrl + path;
		return client.target(uri);
	}

	private final String serverUrl;
}
