package de.sambalmueslie.phonebook.client.rest;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

class RestConnector {
	/**
	 * Constructor.
	 *
	 * @param serverUrl
	 *            {@link #serverUrl}
	 */
	public RestConnector(String serverUrl) {
		this.serverUrl = serverUrl;
		client = ClientBuilder.newClient();
	}

	WebTarget getTarget(String path) {
		final String uri = serverUrl + path;
		return client.target(uri);
	}

	private final Client client;
	private final String serverUrl;
}
