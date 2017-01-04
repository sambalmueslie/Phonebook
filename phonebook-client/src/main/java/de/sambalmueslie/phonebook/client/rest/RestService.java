package de.sambalmueslie.phonebook.client.rest;

import de.sambalmueslie.phonebook.rest.rest.RestAdminService;
import de.sambalmueslie.phonebook.rest.rest.RestInfoService;

public class RestService {

	public RestService(String serverUrl) {
		connector = new RestConnector(serverUrl);

		infoClient = new InfoClient(connector);
		adminClient = new AdminClient(connector);
	}

	public RestAdminService getAdminService() {
		return adminClient;
	}

	public RestInfoService getInfoService() {
		return infoClient;
	}

	private final AdminClient adminClient;
	private final RestConnector connector;
	private final InfoClient infoClient;
}
