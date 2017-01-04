package de.sambalmueslie.phonebook.client.rest;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

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

		final ObjectMapper mapper = new ObjectMapper();
		mapper.disable(MapperFeature.USE_GETTERS_AS_SETTERS);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

	}

	WebTarget getTarget(String path) {
		final String uri = serverUrl + path;
		return client.target(uri);
	}

	private final Client client;
	private final String serverUrl;
}
